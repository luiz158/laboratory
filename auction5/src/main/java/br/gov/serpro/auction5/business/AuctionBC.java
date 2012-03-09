/*
 * Demoiselle Framework
 * Copyright (c) 2009 Serpro and other contributors as indicated
 * by the @author tag. See the copyright.txt in the distribution for a
 * full listing of contributors.
 *
 * Demoiselle Framework is an open source Java EE library designed to accelerate
 * the development of transactional database Web applications.
 *
 * Demoiselle Framework is released under the terms of the LGPL license 3
 * http://www.gnu.org/licenses/lgpl.html  LGPL License 3
 *
 * This file is part of Demoiselle Framework.
 *
 * Demoiselle Framework is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License 3 as published by
 * the Free Software Foundation.
 *
 * Demoiselle Framework is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Demoiselle Framework.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.gov.serpro.auction5.business;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.gov.frameworkdemoiselle.util.ResourceBundle;
import br.gov.serpro.auction5.domain.Auction;
import br.gov.serpro.auction5.domain.Bid;
import br.gov.serpro.auction5.domain.Category;
import br.gov.serpro.auction5.domain.Item;
import br.gov.serpro.auction5.domain.Mode;
import br.gov.serpro.auction5.domain.Status;
import br.gov.serpro.auction5.domain.audit.BidAudit;
import br.gov.serpro.auction5.exception.ApplicationRuntimeException;
import br.gov.serpro.auction5.persistence.AuctionDAO;
import br.gov.serpro.auction5.persistence.BidAuditDAO;
import br.gov.serpro.auction5.persistence.BidDAO;
import br.gov.serpro.auction5.persistence.CategoryDAO;
import br.gov.serpro.auction5.persistence.ItemDAO;

/**
 * @author CETEC/CTJEE
 * @see IBusinessController
 */
public class AuctionBC implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private ResourceBundle bundle;
	
	@Inject
	private AuctionDAO auctionDAO;

	@Inject
	private CategoryDAO categoryDAO;

	@Inject
	private ItemDAO itemDAO;

	@Inject
	private BidDAO bidDAO;

	@Inject
	private BidAuditDAO bidAuditDAO;

	private static Logger log = Logger.getLogger(AuctionBC.class);
	
	public List<Category> listAvailableCategories() {	
		try {
			return categoryDAO.listAvailableCategories();
		} catch (Exception e) {
			throw new ApplicationRuntimeException(bundle.getString("LIST_CATEGORY_NOK", e));
		}
	}

	public List<Item> listItemsByCategory(Category category) {
		try {
			return itemDAO.listByCategory(category);
		} catch (Exception e) {
			throw new ApplicationRuntimeException(bundle.getString("AUCTION_ITEM_LIST_CATEGORY_NOK", e));
		}
	}

	public Auction findAuctionById(Long id) {
		try {
			return auctionDAO.load(id);
		} catch (Exception e) {
			throw new ApplicationRuntimeException(bundle.getString("AUCTION_FIND_BY_ID_NOK", e));
		}
	}

	public List<Auction> listOpenAuctionsByCategory(Category category) {
		try {
			return auctionDAO.listOpenAuctionsByCategory(category);
		} catch (Exception e) {
			throw new ApplicationRuntimeException(bundle.getString("AUCTION_LIST_AUCTIONS_BY_CATEGORY_NOK", e));
		}
	}

	public List<Auction> listOpenAuctionsByItem(Item item) {
		try {
			return auctionDAO.listOpenAuctionsByItem(item);
		} catch (Exception e) {
			throw new ApplicationRuntimeException(bundle.getString("AUCTION_LIST_AUCTIONS_BY_ITEM_NOK", e));
		}
	}

	public List<Auction> listMostOfferedAuctions() {
		return auctionDAO.listMostOfferedAuctions();
	}

	public List<Auction> listCheapestPriceAuctions() {
		return auctionDAO.listCheapestPriceAuctions();
	}

	public List<Auction> listNewestAuctions() {
		return auctionDAO.listNewestAuctions();
	}

	public List<Auction> listEndingSoonAuctions() {
		int twoDays = 2 * 24 * 3600;
		return auctionDAO.listEndingSoonAuctions(twoDays);
	}

	public List<Bid> listLastBidsForAuction(Auction auction) {
		try {
			return bidDAO.listLastBidsForAuction(auction);
		} catch (Exception e) {
			throw new ApplicationRuntimeException(bundle.getString("AUCTION_LIST_LAST_BID_NOK", e));
		}
	}

//	@RequiredRole(roles = AliasRole.ROLE_ADMIN)
    public Auction save(Auction auction) {
//		TO DO
		try {
			
			if (auction.getId() != null) {
				
				List<Bid> listBids = this.listLastBidsForAuction(auction);
				if (listBids != null && listBids.size() > 0) {
					throw new ApplicationRuntimeException(bundle.getString("AUCTION_UPDATE_NOK_THERE_ARE_BIDS"));
				}
				auctionDAO.update(auction);
				
			} else {
				auctionDAO.insert(auction);
			}
			
		} catch (Exception e) {
			throw new ApplicationRuntimeException(bundle.getString("ADM_AUCTION_SAVE_NOK", e));
		}
		return auction;
	}
	
//	@RequiredRole(roles = AliasRole.ROLE_ADMIN)
	public void delete(Auction auction) {
//		TO DO
		try {
			
			auction = auctionDAO.load(auction.getId());
			if (auction == null) {
				throw new ApplicationRuntimeException(bundle.getString("OBJECT_NOT_FOUND"));
			}
			
			List<Bid> listBids = this.listLastBidsForAuction(auction);
			
			if (listBids != null)
				if (listBids.size() > 0)
					throw new ApplicationRuntimeException(bundle.getString("AUCTION_DELETE_NOK_THERE_ARE_BIDS"));
			
			auctionDAO.delete(auction.getId());
			
		} catch (Exception e) {
			throw new ApplicationRuntimeException(bundle.getString("ADM_AUCTION_DELETED_NOK", e));
		}
	}
	
//	@RequiredRole(roles = AliasRole.ROLE_USER)
	public Bid placeBid(Auction auction, Double amount, String login, BidAudit audit) {
//		TO DO

		log.debug("Placing bid of " + amount + " for auction " + auction);
		
		// retrieve the auction instance
		auction = auctionDAO.load(auction.getId());

		// check whether auction is still open
		if (!Status.OPEN.equals(auction.getStatus()))
			throw new ApplicationRuntimeException(bundle.getString("AUCTION_STATUS_CLOSED"));

		// check whether actual modality is auction
		if (Mode.SELLING.equals(auction.getMode()))
			throw new ApplicationRuntimeException(bundle.getString("AUCTION_NO_ENABLED_FOR_BID"));

		Bid bestBid = auction.getBestBid();
		if (bestBid != null) {
			
			// check if amount is better than current winning bid
			if (amount <= bestBid.getAmount()) {
				throw new ApplicationRuntimeException(bundle.getString("AUCTION_AMOUNT_BID_LESSER_BEST_BID"));
			}
			
		} else {
			
			// check if amount is greater than starting price
			if (amount <= auction.getStartingPrice()) {
				throw new ApplicationRuntimeException(bundle.getString("AUCTION_AMOUNT_BID_LESSER_STARTING_PRICE"));
			}
		}
		
		Bid bid = new Bid();
		Date now = Calendar.getInstance().getTime();
		try {
			
			// insert the bid
			bid.setAuction(auction);
			bid.setTimestamp(now);
			bid.setAmount(amount);
			bid.setLogin(login);
			bidDAO.insert(bid);
			
			// update the auction
			auction.setBestBid(bid);
			auction.getBids().add(bid);
			auctionDAO.update(auction);
			
			// insert auditing data
			audit.setDateTime(now);
			bidAuditDAO.insert(audit);
			
		} catch (Exception e) {
			throw new ApplicationRuntimeException(bundle.getString("AUCTION_BID_ITEM_NOK", e));
		}
		
		return bid;
	}

}
