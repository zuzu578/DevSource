
			SELECT ROWNUM ROWNO, T1.* 
			FROM ( SELECT
		TA.COL_id,
          A.COL_NAME as cCollectionTitle
        , TA.COL_NAME as cCollection
        ,A.ADJUST_DATE
        
    FROM KMDB.KMDB_COLLECTIONS A
         INNER JOIN KMDB.KMDB_COLLECTIONS_UPPER B ON A.COL_ID = B.UPPER_COL_NO
         LEFT OUTER JOIN (SELECT COL_ID, COL_NAME FROM KMDB.KMDB_COLLECTIONS) TA ON TA.COL_ID = B.COL_ID
        
        WHERE 1=1
		
          WHERE ROWNUM <= 10
        ) T2
      WHERE ROWNO >=1
	 
      
      
      
	SELECT * FROM (SELECT ROWNUM AS ROWNO, Z.* FROM (
   SELECT DISTINCT T1.SUBJECT AS subject,
		t1.org_file_name AS orgFileName,
			t1.file_name AS fileName,	
      T2.DATA_ID AS dataId,
      T2.SORT1 AS sort1,
      T2.SORT2 AS sort2,
      T2.SORT3  AS sort3,
      T2.SORT4  AS sort4,
      T2.SORT5  AS sort5,
      TA.CODE_NM  AS codeNm,
      T2.CREATE_DATE AS createDate 
     FROM kmdb.KMDB_COLLECTIONS_REL T2 INNER JOIN kmdb.TB_ONLINE_ARCHIVES T1 ON T2.DATA_ID = T1.ARCHIVES_SEQ 
	 INNER JOIN (SELECT CODE_NM ,CODE FROM kmdb.CODEINFO) TA ON TA.CODE = T2.TYPE_CLSS	
	 WHERE 1 = 1
	  
			AND T2.COL_ID =   '7' /**P*/
		 
		 
			 OR T2.SORT1 =  '성춘향' /**P*/
		
		 
		 
			 OR T2.SORT2 =  '1960' /**P*/
		
            		
            		) Z WHERE ROWNUM <=  '1' /**P*/ *  '10' /**P*/) WHERE ROWNO > ( '1' /**P*/ - 1) *  '10' /**P*/