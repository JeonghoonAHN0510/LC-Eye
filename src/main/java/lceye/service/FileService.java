package lceye.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileService {

    private static final String path = "./src/main/resources/json/";

    /**
     * json 파일 작성
     *
     * @param type exchange/result
     * @param name json 파일 이름
     * @param data json 파일에 저장할 데이터
     * @author 민성호
     */
    public boolean writeFile(String type , String name , Map<String,Object> data){
        String fileName = type + "/" + name + ".json";
        // 파일 경로
        String filePath = path + fileName;
        try{
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(filePath);

            // 상위 폴더가 존재하지 않으면 생성
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()){
                parentDir.mkdir();
            }// if end
            mapper.writeValue(file,data);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }// try end
    }// func end

    /**
     * json 파일 Map 타입으로 반환
     *
     * @param type exchange/result
     * @param name json 파일 이름
     * @return Map<String,Object>
     * @author 민성호
     */
    public Map<String,Object> readFile(String type , String name){
        String fileName = type + "/" + name + ".json";
        Map<String ,Object> map = new HashMap<>();
        // 파일 경로
        String filePath = path + fileName;
        try{
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(filePath);
            map = mapper.readValue(file,Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }// try end
        return map;
    }// func end

    /**
     * 파일 검색기능
     *
     * @param name 찾는파일명의 포함되는 문자열
     * @return List<Map<String,Object>>
     * @author 민성호
     */
    public List<Map<String,Object>> filterFile(String name ){
        File filterDir = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String,Object>> list = new ArrayList();
        File[] fileNameList = filterDir.listFiles(new FilenameFilter(){
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".json");
            }
        });
        System.out.println("fileNameList = " + fileNameList);
        try {
            if (fileNameList != null) {
                for (File file : fileNameList) {
                    Map<String, Object> map = mapper.readValue(file, Map.class);
                    list.add(map);
                }// for end
            }// if end
        } catch (Exception e) {
            e.printStackTrace();
        }// try end
        return list;
    }// func end

}// class end
