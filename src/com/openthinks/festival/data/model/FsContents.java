package com.openthinks.festival.data.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import utilities.Checker;
import utilities.CommonUtilities;

/**
 * Class for festival content data <BR>
 * Fixed 12 months fields
 * 
 * @author minjdai
 * 
 */
public class FsContents extends AbstractFsJson {
	private FsMonth Jan;
	private FsMonth Feb;
	private FsMonth Mar;
	private FsMonth Apr;
	private FsMonth May;
	private FsMonth Jun;
	private FsMonth Jul;
	private FsMonth Aug;
	private FsMonth Sep;
	private FsMonth Oct;
	private FsMonth Nov;
	private FsMonth Dec;

	public FsContents() {
		initialContents();
	}

	private transient String countrycode;
	private transient Map<FsMonthType, FsMonth> fsMonthMap;

	private void initialContents() {
		Jan = new FsMonth();
		Feb = new FsMonth();
		Mar = new FsMonth();
		Apr = new FsMonth();
		May = new FsMonth();
		Jun = new FsMonth();
		Jul = new FsMonth();
		Aug = new FsMonth();
		Sep = new FsMonth();
		Oct = new FsMonth();
		Nov = new FsMonth();
		Dec = new FsMonth();
		buildFsMap();
		// {Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec};
	}

	private void buildFsMap() {
		fsMonthMap = new ConcurrentHashMap<>();
		fsMonthMap.put(FsMonthType.Jan, Jan);
		fsMonthMap.put(FsMonthType.Feb, Feb);
		fsMonthMap.put(FsMonthType.Mar, Mar);
		fsMonthMap.put(FsMonthType.Apr, Apr);
		fsMonthMap.put(FsMonthType.May, May);
		fsMonthMap.put(FsMonthType.Jun, Jun);
		fsMonthMap.put(FsMonthType.Jul, Jul);
		fsMonthMap.put(FsMonthType.Aug, Aug);
		fsMonthMap.put(FsMonthType.Sep, Sep);
		fsMonthMap.put(FsMonthType.Oct, Oct);
		fsMonthMap.put(FsMonthType.Nov, Nov);
		fsMonthMap.put(FsMonthType.Dec, Dec);
	}

	/**
	 * rebuild mapping relationship between {@link FsMonthType} and
	 * {@link FsMonth}<BR>
	 * <i>Note</i> invoke this method after deserializing from json
	 */
	public void rebuild() {
		buildFsMap();
		
		for(FsMonthType fsMonthType:FsMonthType.values()){
			FsMonth fsMonth=fsMonthMap.get(fsMonthType);
			for(FsItem item:fsMonth){
				item.setMonth(fsMonthType);
				//TBD
				//item.setCountrycode(getCountrycode());
				for(FsImage image:item.getImages()){
					image.setItemref(item.key());
				}
			}
		}
		
	}

	public void add(FsMonthType month, FsItem item) {
		Checker.require(month).notNull();
		boolean isSuccess=fsMonthMap.get(month).add(item);
		if(isSuccess && item.getMonth()==null){
			item.setMonth(month);
		}
	}
	
	public void add(FsItem item){
		Checker.require(item).notNull();
		Checker.require(item.getMonth()).notNull();
		add(item.getMonth(),item);
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	@Override
	public String key() {
		StringBuilder buider = new StringBuilder();
		buider.append(CommonUtilities.format(Integer.valueOf(getCountrycode()),
				4, 0));
		return buider.toString();
	}
	
	
	public List<FsMonth> months(){
		List<FsMonth> months=new ArrayList<FsMonth>();
		FsMonthType[] fsMonthTypes=FsMonthType.values();
		for(FsMonthType fsMonthType:fsMonthTypes){
			FsMonth fsMonth=fsMonthMap.get(fsMonthType);
			months.add(fsMonth);
		}
		return months;
	}
	
	public static FsContents valueOf(List<FsItem> items){
		FsContents contents=new FsContents();
		
		for(FsItem item:CommonUtilities.requireNotNull(items)){
			contents.add(item);
		}
		
		return contents;
	}

}
