package bookmark.rest.datatables;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class DataTablesResult<T> {

	@JsonProperty("sEcho")
	private Integer echo;

	@JsonProperty("iTotalRecords")
	private Long totalRecords;

	@JsonProperty("iTotalDisplayRecords")
	private Long totalDisplayRecords;

	@JsonProperty("aaData")
	private List<T> data;

	public Integer getEcho() {
		return echo;
	}

	public void setEcho(Integer echo) {
		this.echo = echo;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Long getTotalDisplayRecords() {
		return totalDisplayRecords;
	}

	public void setTotalDisplayRecords(Long totalDisplayRecords) {
		this.totalDisplayRecords = totalDisplayRecords;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
