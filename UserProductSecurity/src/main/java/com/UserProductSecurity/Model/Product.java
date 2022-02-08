package com.UserProductSecurity.Model;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@Column(name="prodid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prodid;
	
	@Column(name="prodname" , nullable = false)
	private String prodname;
	
	@Column(name="prodcode" , nullable = false, unique = true)
	private String prodcode;
	
	@Column(name="prodbrand" , nullable = false)
	private String prodbrand;
	
	@Column(name="proddesc" , nullable = false)
	private String proddesc;
	
	@Column(name="prodprice" , nullable = false)
	private long prodprice;
	
	public Product() {		
		super();		
	}
	
	public Product(int prodid, String prodname, String prodcode, String prodbrand, String proddesc, long prodprice) {
		
		super();
		this.prodid = prodid;
		this.prodname = prodname;
		this.prodcode = prodcode;
		this.prodbrand = prodbrand;
		this.proddesc = proddesc;
		this.prodprice = prodprice;
		
	}
		
	public int getProdid() {
		return prodid;
	}

	public String getProdname() {
		return prodname;
	}

	public String getProdcode() {
		return prodcode;
	}

	public String getProdbrand() {
		return prodbrand;
	}

	public String getProddesc() {
		return proddesc;
	}

	public long getProdprice() {
		return prodprice;
	}

	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public void setProdcode(String prodcode) {
		this.prodcode = prodcode;
	}

	public void setProdbrand(String prodbrand) {
		this.prodbrand = prodbrand;
	}

	public void setProddesc(String proddesc) {
		this.proddesc = proddesc;
	}

	public void setProdprice(long prodprice) {
		this.prodprice = prodprice;
	}

	@Override
	public String toString() {
		return "Product [prodid=" + prodid + ", prodname=" + prodname + ", prodcode=" + prodcode + ", prodbrand="
				+ prodbrand + ", proddesc=" + proddesc + ", prodprice=" + prodprice + "]";
	}
	
	
}
