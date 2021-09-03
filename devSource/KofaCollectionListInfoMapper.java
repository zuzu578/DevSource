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
	/**
	 * kofacollectionList 갯수조회 
	 * @param keyMap
	 * @return
	 */
	public int getKofacollectionListCount(HashMap<String, Object> keyMap) {
		// TODO Auto-generated method stub
		
		return selectOne("kofa.kofacollectionlist.kofaCollectionListCount");
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

	

}
