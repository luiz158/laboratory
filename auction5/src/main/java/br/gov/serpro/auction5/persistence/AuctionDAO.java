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
package br.gov.serpro.auction5.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.auction5.domain.Auction;
import br.gov.serpro.auction5.domain.Category;
import br.gov.serpro.auction5.domain.Status;
import br.gov.serpro.auction5.domain.Mode;
import br.gov.serpro.auction5.domain.Item;

/**
 * This DAO implementation class exhibits the usage of JPQL queries with bind
 * named parameters. Moreover, it exemplifies the usage of native queries
 * programmaticaly and also through named queries.
 * 
 * @author CETEC/CTJEE
 * @see IDAO
 * @see JPAGenericDAO
 */
public class AuctionDAO extends JPACrud<Auction, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Auction> listOpenAuctionsByCategory(Category category) {
		
		String jpql = 
			"select a from Auction a " +
			"where a.status = :status " +
			"  and a.deadline > :deadline " +
			"  and a.item.category = :category " +
			"order by a.item.description";
		
		Query query = createQuery(jpql);
		
		query.setParameter("status", Status.OPEN);
		query.setParameter("deadline", new Date());
		query.setParameter("category", category);
				
		return query.getResultList();
	}
	
	public List<Auction> listMostOfferedAuctions() {
		
		String sql =
			"select d.* from ( " +
			"  select b.auction_id, count(b.id) " +
			"  from bids b " +
			"  inner join auctions a on (a.id = b.auction_id) " +
			"  where a.status = ?1 and a.mode <> ?2 " +
			"  group by b.auction_id " +
			"  order by count(b.id) desc) c " +
			"join auctions d on (d.id = c.auction_id) " +
			"where d.bestbid_id is not null " +
			"order by c.count desc";
		
		Query query = getEntityManager().createNativeQuery(sql, Auction.class);
		
		query.setParameter(1, Status.OPEN.ordinal());
		query.setParameter(2, Mode.SELLING.ordinal());
		
		return query.getResultList();
	}

	public List<Auction> listNewestAuctions() {
		
        Query query = getEntityManager().createNamedQuery("newestAuctions");
		
		query.setParameter(1, Status.OPEN.ordinal());
		
		return query.getResultList();
	}
	
	public List<Auction> listEndingSoonAuctions(int seconds){
		String sql =
			"select * " +
			"from auctions " +
			"where status = ?1 " +
			"  and deadline - current_timestamp between '0 s' and cast(?2 as interval) " +
			"order by deadline ";
		
		Query query = getEntityManager().createNativeQuery(sql, Auction.class);
		
		query.setParameter(1, Status.OPEN.ordinal());
		query.setParameter(2, seconds + " s");
		
		return query.getResultList();
	}

	public List<Auction> listCheapestPriceAuctions() {
		
		// WARNING: use "?" instead of ":" on named parameters for TopLink and EclipseLink
		String sql =
			"select d.* from (" +
			"  select a.id, coalesce(b.amount, a.startingprice, a.sellingprice) as price " +
			"  from auctions a left join bids b on (b.id = a.bestbid_id) " +
			"  where a.status = ?1 " + 
			"  order by 2 asc) c " + 
			"join auctions d on c.id = d.id " +
			"order by c.price";
		
		Query query = getEntityManager().createNativeQuery(sql, Auction.class);
		
		query.setParameter(1, Status.OPEN.ordinal());
		
		return query.getResultList();
	}

	public List<Auction> listOpenAuctionsByItem(Item item) {
		
		Query query = getEntityManager().createNamedQuery("openAuctionsByItem");
		
		query.setParameter(1, Status.OPEN);
		query.setParameter(2, new Date());
		query.setParameter(3, item);
		
		return query.getResultList();
	}
	
	public List<Auction> listAllAuctionsByItem(Item item) {
		
		String jpql = "select a from Auction a where a.item = ?1";
		
		Query query = getEntityManager().createQuery(jpql);
		
		query.setParameter(1, item);
				
		return query.getResultList();
	}

	public Auction insere(Auction auction) {
		
		Date now = Calendar.getInstance().getTime();
		auction.setCreation(now);
		
		super.insert(auction);
		
		return auction;
	}

	public List<Auction> listOpenEndedAuctions(Date timestamp) {
		
		String jpql =
			"select a from Auction a " +
			"where (a.status = ?1) " +
			"  and (a.deadline is not null) " +
			"  and (a.deadline <= ?2)";
		
		Query query = getEntityManager().createQuery(jpql);
		
		query.setParameter(1, Status.OPEN);
		query.setParameter(2, timestamp);
				
		return query.getResultList();
	}
}
