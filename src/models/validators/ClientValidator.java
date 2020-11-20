package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Client;
import utils.DBUtil;

public class ClientValidator {

   public static List<String> validate(Client c,Boolean code_duplicate_check_flag){
       List<String> errors = new ArrayList<String>();

       String code_error = _validateCode(c.getCode(),code_duplicate_check_flag);
       if(!code_error.equals("")){
           errors.add(code_error);
       }

       String name_error = _validateName(c.getName());
       if(!name_error.equals("")){
           errors.add(name_error);
       }
    return errors;

   }

   private static String _validateCode(String code,Boolean code_duplicate_check_flag){
       if(code == null||code.equals("")){
           return "顧客番号を入力してください。";

       }

       if(code_duplicate_check_flag){
           EntityManager em = DBUtil.createEntityManager();
           long clients_count = (long)em.createNamedQuery("checkRegisteredCode",Long.class).setParameter("code", code).getSingleResult();
           em.close();
           if(clients_count > 0){
               return "入力された顧客番号の情報はすでに存在しています。";
           }
       }


    return "";
   }

   private static String _validateName(String name){
       if(name == null || name.equals("")){
           return "氏名を入力してください。";
       }
       return "";
   }

}
