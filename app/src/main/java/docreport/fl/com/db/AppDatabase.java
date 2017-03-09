package docreport.fl.com.db;

import android.content.Context;
import android.content.SharedPreferences;

import java.security.InvalidParameterException;

import docreport.fl.com.doreport.fl.com.utils.Validator;

/**
 * Created by anupak on 3/8/2017.
 */

public class AppDatabase {

    private static AppDatabase instance = new AppDatabase();
    private boolean isDomainInitialized;
    private String domainName;
    private Context ctx;
    private static final String sp = "docreport.fl.com.appdetails";
    private static final String DOMAIN_NAME="domainname";

    private AppDatabase(){

    }

    public static AppDatabase getInstance(Context applicationContext) {
        instance.ctx = applicationContext;
        instance.initialize();
        return instance;
    }

    public static AppDatabase getInstance() {
        return instance;
    }

    private void initialize() {
        readDomainName();
    }

    private void readDomainName() {
        SharedPreferences sharedPref = ctx.getSharedPreferences(sp,Context.MODE_PRIVATE);
        if(sharedPref == null){
            isDomainInitialized = false;
            return;
        }
        domainName = sharedPref.getString(DOMAIN_NAME,null);
        if (domainName == null){
           isDomainInitialized = false;
        }

    }

    public boolean isDomainInitialized() {
        return isDomainInitialized;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) throws Exception {
        if(Validator.validateDomainName(domainName)) {
            SharedPreferences sharedPref = ctx.getSharedPreferences(sp,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(DOMAIN_NAME,domainName);
            this.domainName = domainName;
        } else {
            throw new Exception("Invalid domain name");
        }
    }
}
