package egovframework.ag.homepage.kofacollectionlist.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.ag.common.vo.CommonListVO;

/***********************************************************************************************************************************
 * @class name   		: KofaCollectionListService.java 
 * @author				: jst / jst@ag.cokr
 * @create	 date		: 2021-08-27
 * @package name		: KofaCollectionListService
 * @version 			: v1.0
 * @description 		: kofacollectionList 를 위한 서비스 
 * @history				:
 * =================================================================================
 * DATE      			NAME            	DESCRIPTION
 * ---------------------------------------------------------------------------------------------------------------------------------
 * 2021-08-27    	       주환           	First  Generated
 *************************************************************************************************************************************/

public interface KofaCollectionListService {
	
	/**
	 * kofacollectionlist 조회
	 * @param page
	 * @param pageSize
	 * @return
	 */
	CommonListVO getKofacollection(int page, int pageSize, HashMap<String, Object> keyMap, String sortField,
			String sortWord);
	
	/**
	 * kofacollectionlist 정보 조회
	 * @param keyMap
	 * @return
	 */
	KofaCollectionListVO getCollectionListInfo(String collectionListId);

	/**
	 * 컬렉션 그룹 select 상세 내용 조회
	 * @param keyMap
	 * @return
	 */
	List<Object> getAllKofaCollectionGroup();
	// 컬렉션 사용여부 플래그값 조회 
	List<Object> getuseYnFlag();


}
