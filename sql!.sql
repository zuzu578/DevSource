SELECT T2.* FROM (
			SELECT ROWNUM ROWNO, T1.* 
			
			FROM ( SELECT
		 A.COL_id,
          A.COL_NAME as cCollectionTitle
        , TA.COL_NAME as cCollection
        ,A.ADJUST_DATE
        
    FROM KMDB.KMDB_COLLECTIONS A
         INNER JOIN KMDB.KMDB_COLLECTIONS_UPPER B ON A.COL_ID = B.UPPER_COL_NO
         LEFT OUTER JOIN (SELECT COL_ID, COL_NAME FROM KMDB.KMDB_COLLECTIONS) TA ON TA.COL_ID = B.COL_ID
        
        WHERE 1=1
		   
		   
		) T1
          WHERE ROWNUM <=  10 /**P*/
        ) T2
      WHERE ROWNO >=  1 /**P*/
