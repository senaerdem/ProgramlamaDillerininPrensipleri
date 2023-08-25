/**
*
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 8 Nisan 2023
* <p>
* Main metodunun bulunduğu sınıf
* </p>
*/

package commentcounter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
    	
    	// Javadoc yorumlarını yazacağımız dosya
        File javadocDosya = new File("javadoc.txt");
        // Çok satırlı yorumları yazacağımız dosya
        File cokSatirDosya = new File("coksatir.txt");
        // Tek satırlı yorumları yazacağımız dosya
        File tekSatirDosya = new File("teksatir.txt");
    	
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
        	
        	BufferedWriter javadocWriter = new BufferedWriter(new FileWriter(javadocDosya));
            BufferedWriter cokSatirWriter = new BufferedWriter(new FileWriter(cokSatirDosya));
        	BufferedWriter tekSatirWriter = new BufferedWriter(new FileWriter(tekSatirDosya));
        	
        	
            String satir;
            int javadocSayisi = 0;
            int tekSatirSayisi = 0;
            int cokSatirSayisi = 0;
            boolean cokSatirModu = false;
            String methodIsmi = "";
            
            String sinifIsmi = "";
            
            // Method isimlerini tutmak için bir ArrayList oluştur
            ArrayList<String> methodIsmiList = new ArrayList<String>();


            while ((satir = br.readLine()) != null) {
            	
            	// Sınıf ismi var mı kontrol et
                if (satir.contains("class")) {
                    // Sınıf ismini al
                    Pattern p = Pattern.compile("class\\s+(\\w+)");
                    Matcher m = p.matcher(satir);
                    if (m.find()) {
                    	sinifIsmi = m.group(1);
                    }
                    System.out.println("Sınıf: " + sinifIsmi);
                    System.out.println("---------------------------------------");
                }
                
                // Fonksiyonun üstünde javadoc mu var kontrol et
                if (satir.contains("/**")) {
                	javadocSayisi++;
                    String yorum = satir.substring(satir.indexOf("/**"));
                    javadocWriter.write(yorum + "\n");
                    satir = br.readLine();
                    while (satir != null && !satir.contains("*/")) {
                    	yorum = satir.trim();
                        javadocWriter.write(yorum + "\n");
                        satir = br.readLine();
                    }
                    if (satir != null) {
                    	yorum = satir.substring(0, satir.indexOf("*/") + 2);
                        javadocWriter.write(yorum + "\n");
                        javadocWriter.write("--------------------------------------------\n");
                    }
                }
                
                // Fonksiyon başı mı kontrol et
                if (satir.contains("(") && satir.contains(")") && !satir.contains("=") && !satir.contains(";")) {
                	Pattern p = Pattern.compile("\\s+(\\w+)\\(");
                    Matcher m = p.matcher(satir);
                    if (m.find()) {
                    	methodIsmi = m.group(1);
                    	methodIsmiList.add("Method adı: " + methodIsmi);
                    	
                    	// Method kodunu oku
                        while ((satir = br.readLine()) != null && !satir.contains("}")) {
                        	
                            // Satırda javadoc mu var kontrol et
                            if (satir.contains("/**")) {
                                javadocSayisi++;
                                javadocWriter.write("Fonksiyon: " + methodIsmi + "\n");
                                String yorum = satir.substring(satir.indexOf("/**"));
                                javadocWriter.write(yorum + "\n");
                                satir = br.readLine();
                                while (satir != null && !satir.contains("*/")) {
                                    yorum = satir.trim();
                                    javadocWriter.write(yorum + "\n");
                                    satir = br.readLine();
                                }
                                if (satir != null) {
                                    yorum = satir.substring(0, satir.indexOf("*/") + 2);
                                    javadocWriter.write(yorum + "\n");
                                    javadocWriter.write("--------------------------------------------\n");
                                }
                            }
                            // Satırda tek satırlık yorum mu var kontrol et
                            else if (satir.contains("//")) {
                                tekSatirSayisi++;
                                tekSatirWriter.write("Fonksiyon: " + methodIsmi + "\n");
                                String yorum = satir.substring(satir.indexOf("//"));
                                tekSatirWriter.write(yorum + "\n");
                                tekSatirWriter.write("--------------------------------------------\n");
                            }
                            
                            // Satırda çok satırlık yorum mu var kontrol et
                            else if (satir.contains("/*")) {
                                cokSatirModu = true;
                                cokSatirSayisi++;
                                cokSatirWriter.write("Fonksiyon: " + methodIsmi + "\n");
                                // Satır içinde başka bir karakter var mı diye kontrol et
                                if (!satir.trim().endsWith("/*")) {
                                    cokSatirModu = false;
                                    String yorum = satir.substring(satir.indexOf("/*"), satir.indexOf("*/") + 2);
                                    cokSatirWriter.write(yorum + "\n");
                                    cokSatirWriter.write("--------------------------------------------\n");
                                }
                            } else if (satir.contains("*/")) {
                                cokSatirModu = false;
                                String yorum = satir.substring(0, satir.indexOf("*/") + 2);
                                cokSatirWriter.write(yorum + "\n");
                                cokSatirWriter.write("--------------------------------------------\n");
                            } else if (cokSatirModu) {
                                // Eğer çok satırlı yorum modunda isek her satırı bir arttırmayalım
                           	 String yorum = satir.trim();
                           	    if (!yorum.isEmpty()) {
                           	        cokSatirWriter.write(yorum + "\n");
                           	    }
                                continue;
                            } 
       
                        }
                       
                    }
                    
                 // Fonksiyon sonu mu kontrol et
                    if (satir.contains("}")) {
                        // Yorumu işle
                    	System.out.println("Fonksiyon adı: " + methodIsmi);
                        System.out.println("Tek satır yorum sayısı: " + tekSatirSayisi);
                        System.out.println("Çok satırlı yorum sayısı: " + cokSatirSayisi);
                        System.out.println("Javadoc sayısı: " + javadocSayisi);
                        System.out.println("---------------------------------------");
                        // Değişkenleri sıfırla
                        tekSatirSayisi = 0;
                        cokSatirSayisi = 0;
                        javadocSayisi = 0;
                    }
                   
                }
               
            }
            
            // Dosyaları kapat
            javadocWriter.close();
            cokSatirWriter.close();
            tekSatirWriter.close();
           
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }
    }
}
