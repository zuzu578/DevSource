package egovframework.ag.homepage.kofacollectionlist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.ag.common.utils.ReferenceUtils;
import egovframework.ag.common.utils.SortUtils;
import egovframework.ag.common.utils.StringUtils;
import egovframework.ag.common.vo.CommonListVO;
import egovframework.ag.homepage.admin.menu.service.AdminMenuService;
import egovframework.ag.homepage.common.attach.service.AttachFileService;
import egovframework.ag.homepage.common.attach.service.AttachFileVO;
import egovframework.ag.homepage.kofacollectionlist.service.KofaCollectionListService;
import egovframework.ag.homepage.kofacollectionlist.service.KofaCollectionListVO;
import egovframework.ag.homepage.museum.exhibition.service.ExhibitionInfoVO;

/***********************************************************************************************************************************
 * @class name : KofaCollectionListController.java
 * @author : 주환
 * @create date : 2021-08-27
 * @package name : egovframework.ag.homepage.kofacollectionlist.web
 * @version : v1.0
 * @description : kofacollectionList 관리를 위한 클래스
 * @history :
 *          =================================================================================
 *          DATE NAME DESCRIPTION
 *          ---------------------------------------------------------------------------------------------------------------------------------
 *          2021.08.27 주환 First Generated
 *************************************************************************************************************************************/
@Controller
public class KofaCollectionListController {
	@Autowired
	private AdminMenuService adminMenuService;
	@Autowired
	private KofaCollectionListService kofacollectionListService;
	@Autowired
	private AttachFileService attachFileService;
	private final static Logger logger = LogManager.getLogger();
	/**
	 * kofaCollectionList 자료유형 상세보기 
	 * 
	 * @param commandMap
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/*/collectionDataView")
	public String collectionDataView(@RequestParam HashMap<String, Object> commandMap, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String returnView = "/kofacollectionList/collectionSort/collectionDataView";
		String colId = StringUtils.getStripTag((String) commandMap.get("colId"), null);
		String dataId =StringUtils.getStripTag((String) commandMap.get("dataId"), null);
		
		List<Object> relationShipCollectionTitle = null;
		List<Object> getDescription = null;
		List<Object> getCollectionKofaGroupTitle = null;
		List<Object> collectionDataInfo = null;
		
		getDescription = kofacollectionListService.getDescription(colId);
		relationShipCollectionTitle = kofacollectionListService.getrelationShipCollectionTitle(colId);
		
	
		 getCollectionKofaGroupTitle = kofacollectionListService.getcCollectionKofaGroupTitle(colId);
		 collectionDataInfo = kofacollectionListService.getCollectionDataInfo(colId);
		
		model.addAttribute("colId",colId);
		model.addAttribute("dataId",dataId);
		model.addAttribute("getCollectionKofaGroupTitle",getCollectionKofaGroupTitle);
		
		
		model.addAttribute("getDescription",getDescription);
		model.addAttribute("collectionDataInfo",collectionDataInfo);
		
		return returnView; 
	}
	/**
	 * kofaCollectionList 자료
	 * 
	 * @param commandMap
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/*/kofaCollectionSortList")
	public String kofaCollectionSortList(@RequestParam HashMap<String, Object> commandMap, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String returnView = "/kofacollectionList/collectionSort/collectionSortList";
		logger.debug("kofaCollectionSortList() start!!");
		// contextPath 변수
		String contextPath = request.getContextPath();

		// 페이지 및 목록을 위한 변수
		int totalCount = 0;
		int page = 1;
		int pageSize = 10;

		// 페이지 네비게이션 변수
		String pageNavigation = "";

		// 화면 네비게이션 변수
		String navigation = null;

		// 검색 변수를 위한 변수
		HashMap<String, Object> keyMap = null;
		
		// 정렬을 위한 변수
		Map<String, String> sortMap = null;

		// 검색 변수를 리턴하기 위한 변수
		Map<String, Object> paramMap = null;
		
		List<Object> collectionDataType = null;
		List<Object> collections = null;
		CommonListVO listResult = null;
		String kofaUseYn = StringUtils.getStripTag((String) commandMap.get("kofaUseYn"), null); // 사용여부 (use YN) 
		String dataTitle = StringUtils.getStripTag((String) commandMap.get("dataTitle"), null); // 컬렉션 그룹 
		String kofaCollectionListType = StringUtils.getStripTag((String) commandMap.get("kofaCollectionListType"), null);
		String pageParam = StringUtils.getStripTag((String) commandMap.get("page"));
		String pageSizeParam = StringUtils.getStripTag((String) commandMap.get("pageSize"));
		String keyField = StringUtils.getStripTag((String) commandMap.get("keyField"));
		String keyWord = StringUtils.getStripTag((String) commandMap.get("keyWord"));
		String sortField = StringUtils.getStripTag((String) commandMap.get("sortField"));
		String sortWord = StringUtils.getStripTag((String) commandMap.get("sortWord"));
		String kofaCollectionTitle = StringUtils.getStripTag((String) commandMap.get("kofaCollectionTitle"));
		
		
		
		try {
			paramMap = new HashMap<String, Object>();
			paramMap.put("kofaUseYn", kofaUseYn);
			paramMap.put("dataTitle", dataTitle);
			paramMap.put("kofaCollectionListType", kofaCollectionListType);
			paramMap.put("page", pageParam);
			paramMap.put("pageSize", pageSizeParam);
			paramMap.put("keyField", keyField);
			paramMap.put("keyWord", keyWord);
			paramMap.put("sortField", sortField);
			paramMap.put("sortWord", sortWord);
			paramMap.put("kofaCollectionTitle", kofaCollectionTitle);
			
			if (pageParam != null && !pageParam.equals("")) {
				page = Integer.parseInt(pageParam);
			}
			if (pageSizeParam != null && !pageSizeParam.equals("")) {
				pageSize = Integer.parseInt(pageSizeParam);
			}
			if (keyField == null || keyField.equals("")) {
				keyField = "keyword";
			}
			if (sortField == null || sortField.equals("") || sortWord == null || sortWord.equals("")) {
				sortField = "C_COLLECTION_ID";
				sortWord = "DESC";
			}
			keyMap = new HashMap<String, Object>();
			keyMap.put("kofaCollectionListType", kofaCollectionListType);
			keyMap.put(keyField, keyWord);
			
			keyMap.put("kofaUseYn", kofaUseYn);
			keyMap.put("dataTitle", dataTitle);
			keyMap.put("kofaCollectionTitle", kofaCollectionTitle);
			
			
			listResult = kofacollectionListService.getkofaCollectionSortList(page, pageSize, keyMap, sortField, sortWord);
		
			navigation = ReferenceUtils.getNavigationByPath(contextPath,
					adminMenuService.getNavigationByMenuPath("/kofaCollection/kofaCollectionList"));
			totalCount = listResult.getTotalCount();
			// 컬렉션 그룹 select
			collectionDataType = kofacollectionListService.getAllCollectionDataType();
			
			// 컬렉션 그룹 title select (level2 )
			collections = kofacollectionListService.getcollectionsNames();
			
			model.addAttribute("collectionDataType", collectionDataType);
			pageNavigation = ReferenceUtils.getNavigationByPage(contextPath, totalCount, page, pageSize);
			sortMap = SortUtils.getSortMap("kofaCollectionList", sortField, sortWord);
			model.addAttribute("dataTitle",dataTitle);
			model.addAttribute("navigation", navigation);
			model.addAttribute("pageNavigation", pageNavigation);
			model.addAttribute("collections",collections);
			model.addAttribute("paramMap", paramMap);
			model.addAttribute("sortMap", sortMap);
			model.addAttribute("keyWord", keyWord);
			
			model.addAttribute("page", page);
			model.addAttribute("listResult", listResult);
			model.addAttribute("kofaCollectionTitle",kofaCollectionTitle);

		
		} catch (Exception e) {
			returnView = "/exception/exception";
			logger.debug("Exception kofaCollectionList ===> " + e);
			;
		}
		logger.debug("kofaCollectionList()");
		return returnView;
	}

	/**
	 * kofaCollectionList 그룹
	 * 
	 * @param commandMap
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/*/kofaCollectionGroup")
	public String kofaCollectionGroup(@RequestParam HashMap<String, Object> commandMap, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String returnView = "/kofacollectionList/collectionGroup/collectionGroup";
		String contextPath = request.getContextPath();

		// 페이지 및 목록을 위한 변수
		int totalCount = 0;
		int page = 1;
		int pageSize = 10;

		// 페이지 네비게이션 변수
		String pageNavigation = "";

		// 화면 네비게이션 변수
		String navigation = null;

		// 검색 변수를 위한 변수
		HashMap<String, Object> keyMap = null;
		
		// 정렬을 위한 변수
		Map<String, String> sortMap = null;

		// 검색 변수를 리턴하기 위한 변수
		Map<String, Object> paramMap = null;

		CommonListVO listResult = null;
		
		return returnView;
	}

	/**
	 * kofaCollectionList 목록 호출
	 * 
	 * @param commandMap
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/*/kofaCollectionList")
	public String kofaCollectionList(@RequestParam HashMap<String, Object> commandMap, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.debug("kofaCollectionList() start!!");
		String returnView = "/kofacollectionList/collectionList/collectionList";
		// contextPath 변수
		String contextPath = request.getContextPath();

		// 페이지 및 목록을 위한 변수
		int totalCount = 0;
		int page = 1;
		int pageSize = 10;

		// 페이지 네비게이션 변수
		String pageNavigation = "";

		// 화면 네비게이션 변수
		String navigation = null;

		// 검색 변수를 위한 변수
		HashMap<String, Object> keyMap = null;
		
		// 정렬을 위한 변수
		Map<String, String> sortMap = null;

		// 검색 변수를 리턴하기 위한 변수
		Map<String, Object> paramMap = null;

		List<Object> collectionGroup = null;
		
		List<Object> useYnFlag = null;
		CommonListVO listResult = null;
		String kofaUseYn = StringUtils.getStripTag((String) commandMap.get("kofaUseYn"), null); // 사용여부 (use YN) 
		String KofaGroupTitle = StringUtils.getStripTag((String) commandMap.get("KofaGroupTitle"), null); // 컬렉션 그룹 
		String kofaCollectionListType = StringUtils.getStripTag((String) commandMap.get("kofaCollectionListType"), null);
		String pageParam = StringUtils.getStripTag((String) commandMap.get("page"));
		String pageSizeParam = StringUtils.getStripTag((String) commandMap.get("pageSize"));
		String keyField = StringUtils.getStripTag((String) commandMap.get("keyField"));
		String keyWord = StringUtils.getStripTag((String) commandMap.get("keyWord"));
		String sortField = StringUtils.getStripTag((String) commandMap.get("sortField"));
		String sortWord = StringUtils.getStripTag((String) commandMap.get("sortWord"));
		
		
		try {
			paramMap = new HashMap<String, Object>();
			paramMap.put("kofaUseYn", kofaUseYn);
			paramMap.put("KofaGroupTitle", KofaGroupTitle);
			paramMap.put("kofaCollectionListType", kofaCollectionListType);
			paramMap.put("page", pageParam);
			paramMap.put("pageSize", pageSizeParam);
			paramMap.put("keyField", keyField);
			paramMap.put("keyWord", keyWord);
			paramMap.put("sortField", sortField);
			paramMap.put("sortWord", sortWord);
			if (pageParam != null && !pageParam.equals("")) {
				page = Integer.parseInt(pageParam);
			}
			if (pageSizeParam != null && !pageSizeParam.equals("")) {
				pageSize = Integer.parseInt(pageSizeParam);
			}
			if (keyField == null || keyField.equals("")) {
				keyField = "kofacollectionListName";
			}
			if (sortField == null || sortField.equals("") || sortWord == null || sortWord.equals("")) {
				sortField = "C_COLLECTION_ID";
				sortWord = "DESC";
			}
			keyMap = new HashMap<String, Object>();
			keyMap.put("kofaCollectionListType", kofaCollectionListType);
			keyMap.put(keyField, keyWord);
			keyMap.put("kofaUseYn", kofaUseYn);
			keyMap.put("KofaGroupTitle", KofaGroupTitle);
			
			listResult = kofacollectionListService.getKofacollection(page, pageSize, keyMap, sortField, sortWord);
			// 컬렉션 그룹 select
			collectionGroup = kofacollectionListService.getAllKofaCollectionGroup();
			totalCount = listResult.getTotalCount();

			navigation = ReferenceUtils.getNavigationByPath(contextPath,
					adminMenuService.getNavigationByMenuPath("/kofaCollection/kofaCollectionList"));
			pageNavigation = ReferenceUtils.getNavigationByPage(contextPath, totalCount, page, pageSize);
			sortMap = SortUtils.getSortMap("kofaCollectionList", sortField, sortWord);
			// YN 값 (사용여부 플래그 )
			useYnFlag = kofacollectionListService.getuseYnFlag();
			// List<KofaCollectionListVO>listResult =
			// kofacollectionListService.getKofacollectionList();
			model.addAttribute("useYnFlag", useYnFlag);
			model.addAttribute("navigation", navigation);
			model.addAttribute("pageNavigation", pageNavigation);
			model.addAttribute("collectionGroup", collectionGroup);
			model.addAttribute("paramMap", paramMap);
			model.addAttribute("sortMap", sortMap);
			model.addAttribute("page", page);
			model.addAttribute("listResult", listResult);
			model.addAttribute("kofaUseYn",kofaUseYn); // 사용자가 선택한 useYn 값! 
			model.addAttribute("KofaGroupTitle",KofaGroupTitle); // 사용자가 선택한 kofacollectionGroupTitle 값 !
		} catch (Exception e) {
			returnView = "/exception/exception";
			logger.debug("Exception kofaCollectionList ===> " + e);
			;
		}
		logger.debug("kofaCollectionList()");

		return returnView;
	}
	/**
	 * kofacollectionList 수정 
	 * @param commandMap
	 * @param exhibitionInfoVO
	 * @param bindingResult
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/*/kofaCollectionListEdit")
	public String kofaCollectionListEdit(@RequestParam HashMap<String, Object> commandMap, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String returnView = "/kofacollectionList/collectionList/collectionList";
		logger.debug("kofaCollectionListEdit() start!!");
		// contextPath 변수
		String contextPath = request.getContextPath();

		// 페이지 및 목록을 위한 변수
		int totalCount = 0;
		int page = 1;
		int pageSize = 10;

		// 페이지 네비게이션 변수
		String pageNavigation = "";

		// 화면 네비게이션 변수
		String navigation = null;


		HashMap<String, Object> keyMap = null;

		// 정렬을 위한 변수
		Map<String, String> sortMap = null;

		// 검색 변수를 리턴하기 위한 변수
		Map<String, Object> paramMap = null;

		List<Object> collectionGroup = null;
		
		List<Object> useYnFlag = null;
		CommonListVO listResult = null;
		HashMap<String , Object > upDateParams = null;
		//Request Parameters ! 
		String kofaUseYn = StringUtils.getStripTag((String) commandMap.get("kofaUseYn"), null); // 사용여부 (use YN) 
		String KofaGroupTitle = StringUtils.getStripTag((String) commandMap.get("KofaGroupTitle"), null); // 컬렉션 그룹 
		String kofaCollectionListType = StringUtils.getStripTag((String) commandMap.get("kofaCollectionListType"), null);
		String pageParam = StringUtils.getStripTag((String) commandMap.get("page"));
		String pageSizeParam = StringUtils.getStripTag((String) commandMap.get("pageSize"));
		String keyField = StringUtils.getStripTag((String) commandMap.get("keyField"));
		String keyWord = StringUtils.getStripTag((String) commandMap.get("keyWord"));
		String sortField = StringUtils.getStripTag((String) commandMap.get("sortField"));
		String sortWord = StringUtils.getStripTag((String) commandMap.get("sortWord"));
		// collectionList 정보 paramter! 
		String keyParam = StringUtils.getStripTag((String)commandMap.get("keyParam"),"kofaCollectionListEdit");
		String cCollectionInfo = StringUtils.getStripTag((String) commandMap.get("cCollectionInfo"));
		String cCollectionUseCondition = StringUtils.getStripTag((String) commandMap.get("cCollectionUseCondition"));
		String cCollectionDesc = StringUtils.getStripTag((String) commandMap.get("cCollectionDesc"));
		String cCollectionId = StringUtils.getStripTag((String) commandMap.get("cCollectionId"));
		upDateParams = new HashMap<String , Object>();
		
		// update 쿼리시 필요한 parameters 
		upDateParams.put("cCollectionInfo",cCollectionInfo);
		upDateParams.put("cCollectionUseCondition",cCollectionUseCondition);
		upDateParams.put("cCollectionDesc",cCollectionDesc);
		upDateParams.put("cCollectionId",cCollectionId);
		kofacollectionListService.doUpdate(upDateParams);
		try {
			paramMap = new HashMap<String, Object>();
			paramMap.put("kofaUseYn", kofaUseYn);
			paramMap.put("KofaGroupTitle", KofaGroupTitle);
			paramMap.put("kofaCollectionListType", kofaCollectionListType);
			paramMap.put("page", pageParam);
			paramMap.put("pageSize", pageSizeParam);
			paramMap.put("keyField", keyField);
			paramMap.put("keyWord", keyWord);
			paramMap.put("sortField", sortField);
			paramMap.put("sortWord", sortWord);
			if (pageParam != null && !pageParam.equals("")) {
				page = Integer.parseInt(pageParam);
			}
			if (pageSizeParam != null && !pageSizeParam.equals("")) {
				pageSize = Integer.parseInt(pageSizeParam);
			}
			if (keyField == null || keyField.equals("")) {
				keyField = "kofacollectionListName";
			}
			if (sortField == null || sortField.equals("") || sortWord == null || sortWord.equals("")) {
				sortField = "C_COLLECTION_ID";
				sortWord = "DESC";
			}
			keyMap = new HashMap<String, Object>();
			keyMap.put("kofaCollectionListType", kofaCollectionListType);
			keyMap.put(keyField, keyWord);
			keyMap.put("kofaUseYn", kofaUseYn);
			keyMap.put("KofaGroupTitle", KofaGroupTitle);

			listResult = kofacollectionListService.getKofacollection(page, pageSize, keyMap, sortField, sortWord);
			// 컬렉션 그룹 select
			collectionGroup = kofacollectionListService.getAllKofaCollectionGroup();
			totalCount = listResult.getTotalCount();

			navigation = ReferenceUtils.getNavigationByPath(contextPath,
					adminMenuService.getNavigationByMenuPath("/kofaCollection/kofaCollectionList"));
			pageNavigation = ReferenceUtils.getNavigationByPage(contextPath, totalCount, page, pageSize);
			sortMap = SortUtils.getSortMap("kofaCollectionList", sortField, sortWord);
			// YN 값 (사용여부 플래그 )
			useYnFlag = kofacollectionListService.getuseYnFlag();
			// List<KofaCollectionListVO>listResult =
			// kofacollectionListService.getKofacollectionList();
			model.addAttribute("useYnFlag", useYnFlag);
			model.addAttribute("navigation", navigation);
			model.addAttribute("pageNavigation", pageNavigation);
			model.addAttribute("collectionGroup", collectionGroup);
			model.addAttribute("paramMap", paramMap);
			model.addAttribute("sortMap", sortMap);
			model.addAttribute("page", page);
			model.addAttribute("listResult", listResult);
			model.addAttribute("kofaUseYn",kofaUseYn); // 사용자가 선택한 useYn 값! 
			model.addAttribute("KofaGroupTitle",KofaGroupTitle); // 사용자가 선택한 kofacollectionGroupTitle 값 !

		} catch (Exception e) {
			returnView = "/exception/exception";
			logger.debug("Exception kofaCollectionList ===> " + e);
			;
		}
		
		
		
		
		return returnView;
	}

	/**
	 * 해당 kofaCollectionList 자세히보기 ( 수정페이지로 이동 ) 
	 * 
	 * @param commandMap
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/*/kofaCollectionListInfoView")
	public String kofaCollectionListInfoView(@RequestParam HashMap<String, Object> commandMap,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String returnView = "/kofacollectionList/collectionList/collectionListView";

		AttachFileVO mainImage = null;
		
			
		// 화면 네비게이션 변수
		String navigation = null;

		// 검색 변수를 위한 변수
		HashMap<String, Object> keyMap = null;

		// 검색 변수를 리턴하기 위한 변수
		Map<String, Object> paramMap = null;
		String keyParam = StringUtils.getStripTag((String) commandMap.get("keyParam"), "kofaCollectionListInfoView");
		// null point
		String collectionListId = StringUtils.getStripTag((String) commandMap.get("collectionId"));
		// String exhibitionType = StringUtils.getStripTag((String)
		// commandMap.get("exhibitionType"));
		String pageParam = StringUtils.getStripTag((String) commandMap.get("page"));
		String pageSizeParam = StringUtils.getStripTag((String) commandMap.get("pageSize"));
		String keyField = StringUtils.getStripTag((String) commandMap.get("keyField"));
		String keyWord = (String) commandMap.get("keyWord");
		String sortField = StringUtils.getStripTag((String) commandMap.get("sortField"), "C_COLLECTION_ID");
		String sortWord = StringUtils.getStripTag((String) commandMap.get("sortWord"), "DESC");
		// contextPath 변수
		String contextPath = request.getContextPath();

		KofaCollectionListVO collectionListVo = null; // 해당 클릭한 kofacollection list 에대한 정보 
		KofaCollectionListVO collectiondesc1 = null;// 컬렉션 설명1 ( content )
		KofaCollectionListVO collectiondesc2 = null;// 컬렉션 설명2 ( content )
		KofaCollectionListVO collectiondesc3 = null;// 컬렉션 설명3 ( content )
		
		

		//KofaCollectionListVO pcCollectionListInfoVo = null;

		//KofaCollectionListVO mobileCollectionListInfo = null;

		//KofaCollectionListVO engPcCollectionListInfoVo = null;

		//KofaCollectionListVO engMobileCollectionListInfoVo = null;
		try {
			paramMap = new HashMap<String, Object>();
			paramMap.put("page", pageParam);
			paramMap.put("pageSize", pageSizeParam);
			paramMap.put("keyField", keyField);
			paramMap.put("keyWord", keyWord);
			paramMap.put("sortField", sortField);
			paramMap.put("sortWord", sortWord);
			paramMap.put("keyParam", keyParam);
			// paramMap.put("exhibitionType", exhibitionType);
			
			if (keyParam.equals("kofaCollectionListInfoView") && collectionListId != null
					&& collectionListId.length() > 0) {
				collectionListVo = kofacollectionListService.getCollectionListInfo(collectionListId);
				
				
				collectiondesc1 = kofacollectionListService.getCollectionListInfoContent(collectionListId);
				collectiondesc2 = kofacollectionListService.getCollectionListInfoContent2(collectionListId);
				collectiondesc3 = kofacollectionListService.getCollectionListInfoContent3(collectionListId);
				keyMap = new HashMap<String, Object>();
				// 게시판 코드
				keyMap.put("boardCd", "12");
				keyMap.put("boardNo", collectionListId);

				// 단순이미지
				keyMap.put("categoryCd", "02");
				// 해당게시물에대한 file select
				mainImage = attachFileService.getAttachFileBySearch(keyMap);
				
				keyMap.put("collectionListId", collectionListId);

				// 유입채널코드
				keyMap.put("channelCd", "01");
				
				
			}

			navigation = ReferenceUtils.getNavigationByPath(contextPath,
					adminMenuService.getNavigationByMenuPath("/kofaCollection/kofaCollectionList"));
		} catch (Exception e) {
			returnView = "/exception/exception";
			logger.debug("Exception kofacollectionListview exception === " + e);
			;
		}
		model.addAttribute("navigation", navigation);
		model.addAttribute("paramMap", paramMap);
		model.addAttribute("collectiondesc1",collectiondesc1);
		model.addAttribute("collectiondesc2",collectiondesc2);
		model.addAttribute("collectiondesc3",collectiondesc3);
		
		model.addAttribute("mainImage", mainImage);

		model.addAttribute("collectionListVo", collectionListVo);
		

		return returnView;
	}

	/**
	 * kofaCollectionList 글작성 ( Insert )
	 * 
	 * @param commandMap
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/*/KofaCollectionListForm")
	public String KofaCollectionListForm(HttpServletRequest req) {
		String returnView = "/kofacollectionList/collectionList/collectionListForm";

		return returnView;
	}

}
