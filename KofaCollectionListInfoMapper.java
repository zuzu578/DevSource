package egovframework.ag.homepage.kofacollectionlist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.ag.common.vo.CommonListVO;
import egovframework.ag.homepage.kofacollectionlist.service.KofaCollectionListVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/***********************************************************************************************************************************
 * @class name   		: KofaCollectionListMapper.java 
 * @author				: jst / jst@ag.cokr
 * @create	 date		: 2021-08-27
 * @package name		: egovframework.ag.homepage.exhibition.service.impl
 * @version 			: v1.0
 * @description 		: kofacollectionlist 관리를 위한 클래스 
 * @history				:
 * =================================================================================
 * DATE      			NAME            	DESCRIPTION
 * ---------------------------------------------------------------------------------------------------------------------------------
 * 2021-08-27    	       주환           	First  Generated
 *************************************************************************************************************************************/
@Mapper
public class KofaCollectionListInfoMapper extends EgovAbstractMapper{
	

	public int getkofaCollectionSortListCount(HashMap<String, Object> keyMap) {
		
		return selectOne("kofa.kofacollectionlist.getkofaCollectionSortListCount",keyMap);
	}

	
	/**
	 * kofacollectionList 갯수조회 
	 * @param keyMap
	 * @return
	 */
	public int getKofacollectionListCount(HashMap<String, Object> keyMap) {
		// TODO Auto-generated method stub
		
		return selectOne("kofa.kofacollectionlist.kofaCollectionListCount",keyMap);
	}
	/**
	 * getkofaCollectionSortList 목록 조회 
	 * @param keyMap
	 * @return
	 */
	public List<CommonListVO> getkofaCollectionSortList(HashMap<String, Object> keyMap) {
		// TODO Auto-generated method stub
		return selectList("kofa.kofacollectionlist.getkofaCollectionSortList", keyMap);
	}

	/**
	 * kofacollectionList 목록 조회 
	 * @param keyMap
	 * @return
	 */
	public List<CommonListVO> getKofaCollectionPagingList(HashMap<String, Object> keyMap) {
		// TODO Auto-generated method stub
		return selectList("kofa.kofacollectionlist.getKofaCollectionPagingList", keyMap);
	}
	/**
	 * kofacollectionList 해당 게시물 조회 
	 * @param collectionListId
	 * @return
	 */
	public KofaCollectionListVO getCollectionListInfo(String collectionListId) {
		// TODO Auto-generated method stub
		return selectOne("kofa.kofacollectionlist.getCollectionListInfo", collectionListId);
	}
	/**
	 * kofacollectionList 그룹정보 조회
	 * @param collectionListId
	 * @return
	 */

	public List<Object> getAllKofaCollectionGroup() {
		return  selectList("kofa.kofacollectionlist.getAllKofaCollectionGroup");
		
	}
	/**
	 * kofacollectionList 플래그값 yn 조회 
	 * @param collectionListId
	 * @return
	 */
	public List<Object> getuseYnFlag() {
		
		return selectList("kofa.kofacollectionlist.getuseYnFlag");
	}
	public List<Object> getAllCollectionDataType() {
		// TODO Auto-generated method stub
		return selectList("kofa.kofacollectionlist.getAllCollectionDataType");
	}
	/**
	 * kofacollectionList 내용 수정 
	 * @param upDateParams
	 * @return
	 */
	public void doUpdate(HashMap<String, Object> upDateParams) {
		update("kofa.kofacollectionlist.doUpdate",upDateParams);
		
	}
	/**
	 * kofacollectionList 내용 get 
	 * @param upDateParams
	 * @return
	 * List 로 받아서 한번에 select 하려 했으나 안되서 따로따로 함 
	 */
	public KofaCollectionListVO getCollectionListInfoContent(String collectionListId) {
	
		return selectOne("kofa.kofacollectionlist.getCollectionListInfoContent",collectionListId);
	}

	public KofaCollectionListVO getCollectionListInfoContent2(String collectionListId) {
		// TODO Auto-generated method stub
		return selectOne("kofa.kofacollectionlist.getCollectionListInfoContent2",collectionListId);
	}

	public KofaCollectionListVO getCollectionListInfoContent3(String collectionListId) {
		// TODO Auto-generated method stub
		return selectOne("kofa.kofacollectionlist.getCollectionListInfoContent3",collectionListId);
	}


	public List<Object> getcollectionsNames() {
		// TODO Auto-generated method stub
		return selectList("kofa.kofacollectionlist.getcollectionsNames");
	}


	public List<Object> getrelationShipCollectionTitle(String colId) {
		// TODO Auto-generated method stub
		return selectList("kofa.kofacollectionlist.getrelationShipCollectionTitle",colId);
	}


	public List<Object> getDescription(String colId) {
		// TODO Auto-generated method stub
		return selectList("kofa.kofacollectionlist.getDescription",colId);
	}


	public List<Object> getcCollectionKofaGroupTitle(String colId) {
		// TODO Auto-generated method stub
		return selectList("kofa.kofacollectionlist.getcCollectionKofaGroupTitle",colId);
	}


	public List<Object> getCollectionDataInfo(String colId) {
		// TODO Auto-generated method stub
		return selectList("kofa.kofacollectionlist.getCollectionDataInfo",colId);
	}





	

}
