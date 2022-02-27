package tabula;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

public class id {
private Table table;
private String filename;
private int page;
public id(Table table, String filename, int page) {
	super();
	this.table = table;
	this.filename = filename;
	this.page = page;
}
public Table getTable() {
	return table;
}
public void setTable(Table table) {
	this.table = table;
}
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public int getPage() {
	return page;
}
public void setPage(int page) {
	this.page = page;
}
}
