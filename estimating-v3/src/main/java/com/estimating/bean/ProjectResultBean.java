package com.estimating.bean;

public class ProjectResultBean {
	private String projectName;
	private String description;
	private double usecaseTotalPoint;
	private double functionTotalPoint;
	private int usecaseVersion;
	private int functionVersion;
	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the usecaseTotalPoint
	 */
	public double getUsecaseTotalPoint() {
		return usecaseTotalPoint;
	}
	/**
	 * @param usecaseTotalPoint the usecaseTotalPoint to set
	 */
	public void setUsecaseTotalPoint(double usecaseTotalPoint) {
		this.usecaseTotalPoint = usecaseTotalPoint;
	}
	/**
	 * @return the functionTotalPoint
	 */
	public double getFunctionTotalPoint() {
		return functionTotalPoint;
	}
	/**
	 * @param functionTotalPoint the functionTotalPoint to set
	 */
	public void setFunctionTotalPoint(double functionTotalPoint) {
		this.functionTotalPoint = functionTotalPoint;
	}
	/**
	 * @return the usecaseVersion
	 */
	public int getUsecaseVersion() {
		return usecaseVersion;
	}
	/**
	 * @param usecaseVersion the usecaseVersion to set
	 */
	public void setUsecaseVersion(int usecaseVersion) {
		this.usecaseVersion = usecaseVersion;
	}
	/**
	 * @return the functionVersion
	 */
	public int getFunctionVersion() {
		return functionVersion;
	}
	/**
	 * @param functionVersion the functionVersion to set
	 */
	public void setFunctionVersion(int functionVersion) {
		this.functionVersion = functionVersion;
	}
}
