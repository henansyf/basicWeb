package cn.goodata.demo.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;




public class Pager extends Page {
    private Integer page;
	public Pager() {
		this.size=5;

	}


	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
		this.current=page;
	}
}