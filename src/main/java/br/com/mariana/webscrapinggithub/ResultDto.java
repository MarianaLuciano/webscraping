package br.com.mariana.webscrapinggithub;

public class ResultDto {
	
	private Integer count;
	
	private Integer lines;
	
	private double bytes;
	
	public ResultDto() {
		
	}

	public ResultDto(int lines2, double size) {
		this.lines = lines2; 
		this.bytes = size;
		this.count = 1;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getLines() {
		return lines;
	}

	public void setLines(Integer lines) {
		this.lines = lines;
	}

	public double getBytes() {
		return bytes;
	}

	public void setBytes(double bytes) {
		this.bytes = bytes;
	}
	
	public int countLines(int addLine) {
		return this.lines += addLine;
	}
	
	public double countSize(double countSize) {
		return bytes += countSize;
	}
	
	public void addQuantity() {
		this.count++;
	}
	
}
