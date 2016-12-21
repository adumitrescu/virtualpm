/**
 * 
 */
package com.vm.model;

import java.util.List;

/**
 * @author crpaslaru
 * @param <T>
 *
 */
public class ResponseModel<T> {

	private List<T> list;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
