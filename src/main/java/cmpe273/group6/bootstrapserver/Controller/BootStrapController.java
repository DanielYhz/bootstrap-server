package cmpe273.group6.bootstrapserver.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.HashMap;

@RestController
@RequestMapping("/bs")
public class BootStrapController {

    private HashMap<String, Integer> map;
    String url = "http://localhost:8088";
    // Here, if the value is 1, means it's being bootstrapped.

    BootStrapController() {
        this.map = new HashMap<>();
//        map.put("SJSUSEN001", 0);
//        map.put("SJSUCAM001",0);
//        map.put("SJSUSPR001",0);
    }

//    @GetMapping("/{id}")
//    public String writeTo( @PathVariable("id") String fid) {
//        ///System.out.println("aaa");
//        if (map.containsKey(fid)) {
//            if (map.get(fid) == 0) {
//                map.put(fid,1);
//                return url;
//            } else {
//                return "The device is being bootstrapped";
//            }
//        } else {
//            return "Invalid Device";
//        }
//    }

    @GetMapping("/{id}")
    public String writeTo(@PathVariable("id") String fid) {
        if (map.containsKey(fid)) {
            return "The device is being bootstrapped already";
        } else {
            if (fid.matches("SJSUSEN[0-9]+") || fid.matches("SJSUCAM[0-9]+") || fid.matches("SJSUSPR[0-9]+")) {
                map.put(fid, 1);
                return url;
            } else {
                return "Invalid Device";
            }
        }
    }

    @GetMapping("/discover/{id}")
    public String discover( @PathVariable("id") String fid) {
//        if (!map.containsKey(fid)) {
//            return "Invalid Device";
//        } else if (map.get(fid) == 0) {
//            return "Not Boot Strapped Device";
//        } else {
//            return "Boot Strapped Device";
//        }
        if (fid.matches("SJSUSEN[0-9]+") || fid.matches("SJSUCAM[0-9]+") || fid.matches("SJSUSPR[0-9]+")) {
            if (!map.containsKey(fid)) {
                return "Not yet being boot strapped";
            } else {
                return "Boot Strapped Device";
            }
        } else {
            return "Invalid Device";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete( @PathVariable("id") String fid) {
        if (!map.containsKey(fid)) {
            return "Invalid Device";
        } else {
            map.remove(fid);
            return "Success Delete" + fid;
        }
    }
}

