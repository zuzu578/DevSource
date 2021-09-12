package egovframework.ag.homepage.kofacollectionlist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.ag.common.vo.CommonListVO;
import egovframework.ag.homepage.kofacollectionlist.service.KofaCollectionListService;
import egovframework.ag.homepage.kofacollectionlist.service.KofaCollectionListVO;
import egovframework.ag.homepage.museum.exhibition.service.ExhibitionInfoService;
import egovframework.ag.homepage.museum.exhibition.service.ExhibitionInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/***********************************************************************************************************************************
 * @class name   		: KofaCollectionListServiceimpl.java 
 * @author				: jst / jst@ag.cokr
 * @create	 date		: 2021-08-27
 * @package name		: KofaCollectionListServiceimpl.java 
 * @version 			: v1.0
 * @description 		: kofacollectionlist 관리를 위한 비즈니스 구현 클래스 
 * @history				:
 * =================================================================================
 * DATE      			NAME            	DESCRIPTION
 * ---------------------------------------------------------------------------------------------------------------------------------
 * 2021. 08.27.     	 주환           	First  Generated
 *************************************************************************************************************************************/

@Service
public class KofaCollectionListServiceImpl extends EgovAbstractServiceImpl implements KofaCollectionListService {
	private final static Logger logger = LogManager.getLogger();
	@Autowired
	private KofaCollectionListInfoMapper kofacollectionListInfoMapper;
	@Override
	public List<Object> getrelationShipCollectionTitle(String colId) {
		// TODO Auto-generated method stub
		return  kofacollectionListInfoMapper.getrelationShipCollectionTitle(colId);
	}
	@Override
	public CommonListVO getkofaCollectionSortList(int page, int pageSize, HashMap<String, Object> keyMap,
			String sortField, String sortWord) {
		int getkofaCollectionSortListCount =kofacollectionListInfoMapper.getkofaCollectionSortListCount(keyMap);
		int startRow = ((page - 1) * pageSize) + 1;
		int fetchSize = page * pageSize;
		keyMap.put("startRow", startRow);
		keyMap.put("fetchSize", fetchSize);
		keyMap.put("sortField", sortField);
		keyMap.put("sortWord", sortWord);
		List<CommonListVO> getkofaCollectionSortList = kofacollectionListInfoMapper.getkofaCollectionSortList(keyMap);
		
		return new CommonListVO(page, pageSize,  getkofaCollectionSortListCount, getkofaCollectionSortList);
	
	}
	
	//페이징 kofaCollectionList 목록  
	@Override
	public CommonListVO getKofacollection(int page, int pageSize, HashMap<String, Object> keyMap, String sortField,
			String sortWord) {
		int kofacollectionListCount =kofacollectionListInfoMapper.getKofacollectionListCount(keyMap);
		int startRow = ((page - 1) * pageSize) + 1;
		int fetchSize = page * pageSize;
		keyMap.put("startRow", startRow);
		keyMap.put("fetchSize", fetchSize);
		keyMap.put("sortField", sortField);
		keyMap.put("sortWord", sortWord);
		List<CommonListVO> kofaCollectionList = kofacollectionListInfoMapper.getKofaCollectionPagingList(keyMap);
		//String title =  (String) keyMap.get("kofaCollectionListTitle").toString();
		 
		System.out.println("kofacollectionListCount ===========================> " +  kofacollectionListInfoMapper.getKofacollectionListCount(keyMap));
		return new CommonListVO(page, pageSize,  kofacollectionListCount, kofaCollectionList);
	}
	@Override
	public KofaCollectionListVO getCollectionListInfo(String collectionListId) {
		return kofacollectionListInfoMapper.getCollectionListInfo(collectionListId);
	}

	
	// 컬렉션 그룹 select 
	@Override
	public List<Object> getAllKofaCollectionGroup() {
		
		return  kofacollectionListInfoMapper.getAllKofaCollectionGroup();
	}
	@Override
	public List<Object> getAllCollectionDataType() {
		return kofacollectionListInfoMapper.getAllCollectionDataType();
	}
	@Override
	public List<Object> getcollectionsNames() {
		// TODO Auto-generated method stub
		return kofacollectionListInfoMapper.getcollectionsNames();
	}

	@Override
	public List<Object> getuseYnFlag() {
		
		return kofacollectionListInfoMapper.getuseYnFlag();
	}
	@Override
	public void doUpdate(HashMap<String, Object> upDateParams) {
		kofacollectionListInfoMapper.doUpdate(upDateParams);
		
	}
	@Override
	public KofaCollectionListVO getCollectionListInfoContent(String collectionListId) {
		
		return kofacollectionListInfoMapper.getCollectionListInfoContent(collectionListId);
	}
	@Override
	public KofaCollectionListVO getCollectionListInfoContent2(String collectionListId) {
		
		return kofacollectionListInfoMapper.getCollectionListInfoContent2(collectionListId);
	}
	@Override
	public KofaCollectionListVO getCollectionListInfoContent3(String collectionListId) {
		// TODO Auto-generated method stub
		return kofacollectionListInfoMapper.getCollectionListInfoContent3(collectionListId);
	}
	@Override
	public List<Object> getDescription(String colId) {
		// TODO Auto-generated method stub
		return kofacollectionListInfoMapper.getDescription(colId);
	}
	@Override
	public List<Object> getcCollectionKofaGroupTitle(String colId) {
		// TODO Auto-generated method stub
		return kofacollectionListInfoMapper.getcCollectionKofaGroupTitle(colId);


	
	}
	@Override
	public List<Object> getCollectionDataInfo(String colId) {
		// TODO Auto-generated method stub
		return kofacollectionListInfoMapper.getCollectionDataInfo(colId);
	}



	
	

}
