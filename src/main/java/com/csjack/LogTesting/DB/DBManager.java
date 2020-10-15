package com.csjack.LogTesting.DB;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Slf4j
@Service
public class DBManager {
    //private static final Logger log = LoggerFactory.getLogger(DBManager.class);

    public DBManager(){
        log.trace("constructor entry");
        getConnection();
        log.trace("constructor exit");
    }

    private Integer getConnection(){
        log.trace("method entry");

        log.info("DB connected");

        log.trace("method exit");
        return 0;
    }

    public Integer Disconnect(){
        log.trace("method entry");

        log.info("DB disconnected");

        log.trace("method exit");
        return 0;
    }

    public String dummySelect(String SCAC, String convertTypeId, String TP_ID){
        return SCAC;
    }

    public String selectRecord(String SCAC, String convertTypeId, String TP_ID) throws SQLException, NullPointerException {
        log.trace("method entry");
        log.debug("SCAC {}, convertTypeId {}, TP_ID {}", SCAC, convertTypeId, TP_ID);

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
            //log.error("Exception with parameters SCAC {}, convertTypeId {}, TP_ID {} and SQL {}", SCAC, convertTypeId, TP_ID, sql);
            throw ex;
        } catch (NullPointerException ex) {
            //log.error("Exception with parameters SCAC {}, convertTypeId {}, TP_ID {} and SQL {}", SCAC, convertTypeId, TP_ID, sql);
            throw ex;
        } finally {
            if(pre != null){
                pre.close();
            }
            if (result != null){
                result.close();
            }
        }
        log.debug("the result ext_cde to return is {}", ext_cde);
        log.trace("method exit");
        return ext_cde;
    }
}
