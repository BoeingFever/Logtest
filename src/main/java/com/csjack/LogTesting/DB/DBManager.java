package com.csjack.LogTesting.DB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Service
public class DBManager {
    private static final Logger logger = LoggerFactory.getLogger(DBManager.class);

    public DBManager(){
        logger.trace("constructor entry");
        getConnection();
        logger.trace("constructor exit");
    }

    private Integer getConnection(){
        logger.trace("method entry");

        logger.info("DB connected");

        logger.trace("method exit");
        return 0;
    }

    public Integer Disconnect(){
        logger.trace("method entry");

        logger.info("DB disconnected");

        logger.trace("method exit");
        return 0;
    }

    public String dummySelect(String SCAC, String convertTypeId, String TP_ID){
        return SCAC;
    }

    public String selectRecord(String SCAC, String convertTypeId, String TP_ID) throws SQLException, NullPointerException {
        logger.trace("method entry");
        logger.debug("SCAC {}, convertTypeId {}, TP_ID {}", SCAC, convertTypeId, TP_ID);

        String sql = "select ext_cde from b2b_edi_cde_ref where convert_type_id =? and tp_id =? and scac = ?";
        PreparedStatement pre = null;
        ResultSet result = null;
        String ext_cde=null;
        try {
            pre.setString(1, convertTypeId);
            pre.setString(2, TP_ID);
            pre.setString(3, SCAC);
            result = pre.executeQuery();

            if (result.next()) {
                ext_cde = result.getString(1);
            }
        } catch (SQLException ex) {
            //logger.error("Exception with parameters SCAC {}, convertTypeId {}, TP_ID {} and SQL {}", SCAC, convertTypeId, TP_ID, sql);
            throw ex;
        } catch (NullPointerException ex) {
            //logger.error("Exception with parameters SCAC {}, convertTypeId {}, TP_ID {} and SQL {}", SCAC, convertTypeId, TP_ID, sql);
            throw ex;
        } finally {
            if(pre != null){
                pre.close();
            }
            if (result != null){
                result.close();
            }
        }
        logger.debug("the result ext_cde to return is {}", ext_cde);
        logger.trace("method exit");
        return ext_cde;
    }
}
