package teammates.ui.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import teammates.common.datatransfer.AccountAttributes;

public class ImageResult extends ActionResult {

    public String blobKey;
    
    public ImageResult(String destination, String blobKey, AccountAttributes account,
            Map<String, String[]> parametersFromPreviousRequest,
            List<String> status) {
        super(destination, account, parametersFromPreviousRequest, status);
        this.blobKey = blobKey;
    }

    @Override
    public void send(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        
        if (blobKey != "") {
            BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
            blobstoreService.serve(new BlobKey(blobKey), resp);
        } else {
            resp.sendError(1, "No image found");;
        }
    }

}
