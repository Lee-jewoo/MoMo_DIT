package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("LocationDTO")
public class LocationDTO {

	int loc_num;
	String loc;
	int loc_level;
	int par_loc_num;
	String loc_path;
	
	public LocationDTO() {}
	public LocationDTO(int loc_num, String loc, int loc_level, int par_loc_num, String loc_path) {
		this.loc_num = loc_num;
		this.loc = loc;
		this.loc_level = loc_level;
		this.par_loc_num = par_loc_num;
		this.loc_path = loc_path;
	}
	
	public int getLoc_num() {
		return loc_num;
	}
	public void setLoc_num(int loc_num) {
		this.loc_num = loc_num;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public int getLoc_level() {
		return loc_level;
	}
	public void setLoc_level(int loc_level) {
		this.loc_level = loc_level;
	}
	public int getPar_loc_num() {
		return par_loc_num;
	}
	public void setPar_loc_num(int par_loc_num) {
		this.par_loc_num = par_loc_num;
	}
	public String getLoc_path() {
		return loc_path;
	}
	public void setLoc_path(String loc_path) {
		this.loc_path = loc_path;
	}
	@Override
	public String toString() {
		return "LocationDTO [loc_num=" + loc_num + ", loc=" + loc + ", loc_level=" + loc_level + ", par_loc_num="
				+ par_loc_num + ", loc_path=" + loc_path + "]";
	}
	
}
