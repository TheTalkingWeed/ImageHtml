import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MyFileUtils {
    public static List<File> getMappanevek(File file){
        List<File> output = new ArrayList<>();
        File[] files=file.listFiles();
        for(File e : files){
            if(e.isDirectory()) {
                output.add(e);
                output.addAll(getMappanevek(e));
            }
        }
        return output;
    }


    public static List<File> getPicture(File f){
        File[] files=f.listFiles();
        List<File> output= new ArrayList<>();
        for(File e : files){
            if(isPicture(e)) {
                output.add(e);

            }
        }
        return output;
    }

    public static List<File> createHtml(List<File> s, File alap) throws Exception{
        List<File> output = new ArrayList<>();
        List<URI> segedlinkek = new ArrayList<>();
        URI uri;
        for (File k: s){
            segedlinkek.add(k.toURI());
        }
        int index=0;
        for(File e:s){

            File f = new File(e.getAbsolutePath().replace(".jpg","")+".html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));


            bw.write("<!DocType Html>\n");
            bw.write("<Html>\n");
            bw.write("<Head>");
            bw.write("<Title>");
            bw.write("</Title>");
            bw.write("</Head>");
            bw.write("  <a style=\"color:black;font-size:40px;\" href=" + '"'+alap+"\\index.html"+'"' + ">Start Page</a>");
            bw.write("<li><a style=\"color:black;font-size:20px;\" href="+'"'+e.getParent()+"\\index.html"+'"'+">Back</a></li>");


            if (index==0) {
                bw.write(" <a style=\"color:red;font-size:10px;\"><<</a>");
            } else
                bw.write("  <a style=\"color:red;font-size:10px;\" href=" + '"' + segedlinkek.get(index - 1).toString().replace(".jpg", ".html") + '"' + "><<</a>");

            bw.write(" <a style=\"color:black;font-size:20px;\">"+e.getName()+"</a>");

            if (index==segedlinkek.size()-1){
                bw.write(" <a style=\"color:red;font-size:10px;\">>></a>");
            } else
                bw.write("  <a style=\"color:red;font-size:10px;\" href=" + '"' + segedlinkek.get(index + 1).toString().replace(".jpg", ".html") + '"' + ">>></a>");

            bw.write("<br>");

            if (index<segedlinkek.size()-1) {
                bw.write("<a href=" + segedlinkek.get(index + 1).toString().replace(".jpg",".html")  +">");

            }
            bw.write("<img src = "+'"'+e+'"'+"width="+'"'+450+'"'+"height="+'"'+300+'"'+">");

            bw.write("</Body>\n");
            bw.write("</Html>\n");
            bw.close();

            output.add(f);
            index++;
         }
        return output;
    }

    public static void htmlToLife(List<File> temp) throws Exception {

        for (File e : temp) {
            File file = new File(e.toString());
        }
    }
    public static void fajlTorol(List<File> s){

        for(File e : s){
            File[] files=e.listFiles();
            for(File k: files)
                if(isHtml(k)) {
                    k.delete();
                }
        }

    }

    public static void makeIndexfile(File fajl,File alap) throws  Exception{
       URI uri;
       File[] segdfajlok = fajl.listFiles();

       File tempfile = new File(fajl + "\\index.html");
       BufferedWriter bw = new BufferedWriter(new FileWriter(tempfile));
       bw.write("<!DocType Html>\n");
       bw.write("<Html>\n");
       bw.write("  <a style=\"color:black;font-size:40px;\" href=" + '"'+alap+"\\index.html"+'"' + ">Start Page</a>");
       bw.write("<Head>");
       bw.write(" <h1 style=\"color:black;font-size:40px;\">Directories</h1>");

        if (fajl.getParent().equals("C:\\")) {
            bw.write("");
        }else if(fajl.getParentFile()!=null){
           bw.write("<li><a style=\"color:black;font-size:20px;\" href="+'"'+fajl.getParent()+"\\index.html"+'"'+">Back</a></li>");
       }



       for(File w:segdfajlok) {
           if (w.isDirectory()) {
               uri = w.toURI();
               bw.write("<li><a href=" + '"' + uri + "\\index.html" + '"' + ">" + w.getName() + "</a></li>");
           }

       }

       bw.write("<Title>");
       bw.write("Insert an Image");
       bw.write("</Title>");
       bw.write("</Head>");
       bw.write(" <h1 style=\"color:black;font-size:40px;\">Images</h1>");
       for (File k : segdfajlok) {
           if (isHtml(k)) {
               uri = k.toURI();
               bw.write("<li>   <a href=" + '"' + uri + '"' + ">" + k.getName().replace(".html", ".jpg") + "</a></li>");
           }
       }
       bw.write("</Body>\n");
       bw.write("</Html>\n");
       bw.close();

    }

    private static boolean isPicture(File f){
        String str=f.toString();
        return  str.contains(".jpg") || str.contains(".jpeg") || str.contains(".png")  ;
    }

    private static boolean isHtml(File file){
        String str= file.toString();
        return str.contains(".html");
    }



}
