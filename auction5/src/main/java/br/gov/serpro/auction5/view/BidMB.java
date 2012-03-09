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
package br.gov.serpro.auction5.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.security.AuthorizationException;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.util.ResourceBundle;
import br.gov.serpro.auction5.business.AuctionBC;
import br.gov.serpro.auction5.constant.AliasNavigationRule;
import br.gov.serpro.auction5.domain.Auction;
import br.gov.serpro.auction5.domain.Bid;
import br.gov.serpro.auction5.domain.audit.BidAudit;
import br.gov.serpro.auction5.exception.ApplicationRuntimeException;

/**
 * @author CETEC/CTJEE
 * @see AbstractManagedBean
 */
@ViewController
public class BidMB implements Serializable, AliasNavigationRule {

	private static final long serialVersionUID = 1L;

	@Inject
	private AuctionBC auctionBC;

	private Auction auction;
	// TO DO
	private String userName = "admin";
	private Double amount;
	
	private List<Bid> listLastBids;

	private static final int BIDS_LIST_COUNT = 10;
	
	@Inject
	private MessageContext messageContext;
	
	@Inject
	private ResourceBundle bundle;
	
	public BidMB() {
		try {
			
			// as the bean is in session scope, we'll retrieve the user once
//			ISecurityContext ctx = ContextLocator.getInstance().getSecurityContext();
//			this.userName = ctx.getUserPrincipal().getName();
//			TO DO
			
		} catch (ApplicationRuntimeException e) {
			throw e;
		}
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<Bid> getListLastBids() {
		return listLastBids;
	}

	public void setListLastBids(List<Bid> listBids) {
		this.listLastBids = listBids;
	}

	private void reloadLastBidsList() {
		if (this.auction != null) {
			this.listLastBids = auctionBC.listLastBidsForAuction(this.auction);
		} else {
			this.listLastBids = null;
		}
	}
	
	/**
	 * Action fired when user clicks a "Place a Bid" link.
	 * 
	 * @return String
	 */
	public String actionPreBid() {
		
		this.amount = null;
		reloadLastBidsList();
		
		return ALIAS_BID;
	}
	
	/**
	 * Action fired when the user finally places a bid, after clicking "Place Bid" link.
	 * 
	 * @return	a String
	 */
	public String actionBid() {
		try {
			
			HttpServletRequest request = (HttpServletRequest)
				FacesContext.getCurrentInstance().getExternalContext().getRequest();
			BidAudit audit = createBidAudit(request);
			
			Bid bid = auctionBC.placeBid(this.auction, this.amount, this.userName, audit);
			messageContext.add(bundle.getString("BID_ITEM_OK"));
			
			this.auction.setBestBid(bid);
			this.amount = null;
			reloadLastBidsList();
			
		} catch (ApplicationRuntimeException e) {
			throw e; 
		} catch (AuthorizationException e) {
			messageContext.add(bundle.getString("USER_NOT_AUTHORIZED"));
		}
		return ALIAS_STAY;
	}

	/**
	 * Creates a bid auditing entity based on the given HTTP request.
	 * 
	 * @param request	an HttpServletRequest
	 * @return	a BidAudit
	 */
	private BidAudit createBidAudit(HttpServletRequest request) {
		
		BidAudit audit = new BidAudit();
		
		audit.setHost(request.getRemoteHost());
		audit.setUser(request.getRemoteUser());
		audit.setSession(request.getRequestedSessionId());
		audit.setEncoding(request.getCharacterEncoding());
		audit.setLocale(request.getLocale().toString());
		audit.setAgent(request.getHeader("user-agent"));
		
		return audit;
	}
	
}
