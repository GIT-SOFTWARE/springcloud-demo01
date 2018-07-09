package com.biostime.app.settings.entity;

// Generated 2016-1-27 11:46:39 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * DlOaInterfaceLog generated by hbm2java
 */
@Entity
@Table(name = "DL_OA_INTERFACE_LOG", schema = "DEALER_PLAT")
public class DlOaInterfaceLog implements java.io.Serializable {

	private DlOaInterfaceLogId id;

	public DlOaInterfaceLog() {
	}

	public DlOaInterfaceLog(DlOaInterfaceLogId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "oaApplyId", column = @Column(name = "OA_APPLY_ID", length = 50)),
			@AttributeOverride(name = "interfaceName", column = @Column(name = "INTERFACE_NAME", length = 150)),
			@AttributeOverride(name = "inParam", column = @Column(name = "IN_PARAM", length = 4000)),
			@AttributeOverride(name = "handleResult", column = @Column(name = "HANDLE_RESULT", length = 100)),
			@AttributeOverride(name = "handleMessage", column = @Column(name = "HANDLE_MESSAGE", length = 150)),
			@AttributeOverride(name = "applyTime", column = @Column(name = "APPLY_TIME", length = 50)),
			@AttributeOverride(name = "insertTime", column = @Column(name = "INSERT_TIME", length = 7)) })
	public DlOaInterfaceLogId getId() {
		return this.id;
	}

	public void setId(DlOaInterfaceLogId id) {
		this.id = id;
	}

}
