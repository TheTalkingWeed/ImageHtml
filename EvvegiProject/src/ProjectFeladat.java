import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProjectFeladat {
    public static void main(String[] args) throws Exception{

        if (args.length==0){
            System.out.println("Hiba: adja meg a konytar eleresi utvonalat!");
            System.exit(1);
        } else if(args.length==1){
            File segedfajl=new File(args[0]);
            if (segedfajl.isDirectory()) {

                    List<File> mappanevek = new ArrayList<File>(MyFileUtils.getMappanevek(segedfajl));
                    mappanevek.add(segedfajl);

                    MyFileUtils.fajlTorol(mappanevek);


                    for (File e : mappanevek) {

                       MyFileUtils.htmlToLife(MyFileUtils.createHtml(MyFileUtils.getPicture(e),segedfajl));
                    }
                    for(File e:mappanevek) {
                        MyFileUtils.makeIndexfile(e,segedfajl);
                    }

            }
                else
             System.out.println("Hiba: A megadott konyvtar nem letezik!");
        }
        System.out.println("Program finished");
    }
}

