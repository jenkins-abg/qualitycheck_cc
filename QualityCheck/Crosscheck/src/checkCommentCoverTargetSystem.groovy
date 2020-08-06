/*****************************************************************************************
* This class will only check the Comment_Cover Result of each test design 
* usage and other relevant information for the method as description.
*****************************************************************************************/

/*****************************************************************************************
* Author    Paul Ryan A. Dedumo
* Version   0.0.1
* History   Updated By      Update Date     Update Reason
*           ==============  ===========     =============================================
*****************************************************************************************/
import groovy.transform.SourceURI
import java.nio.file.Path
import java.nio.file.Paths

class CheckCommentCover { 

   static void main(String[] args) {
      
      int softwareFolderCount;
      int fileFolderCount;
      int i;
      int j;

      def initSoftwareCount = 30;
      def initLabelCount = 30;
      def initTDCount = 30;

      String [][] myData = new String [initTDCount][initSoftwareCount];
      
      fileFolderCount = 0;
      softwareFolderCount = 0;

      SoftwareProperties swp = new SoftwareProperties();

      try {
         new File(swp.getRelPath() + '/td').eachDirRecurse() {
            swp.swLabelName = it;
            myData[fileFolderCount][softwareFolderCount] = it.getName() + ' == ' + swp.getTargetSystem();
            softwareFolderCount++
         }
      }catch(Exception ex) {
         softwareFolderCount = 0;
      }

      try {
         new File(swp.getRelPath() + '/td').eachFileRecurse() {
            fileFolderCount++
         }
      }catch(Exception ex) {
         fileFolderCount = 0;
      }

      for(i = 0; i < softwareFolderCount ; i++) {
         println myData[fileFolderCount][i];
      }

   }
} 

class SoftwareProperties {
   
   @SourceURI
   URI sourceUri
   Path scriptLocation = Paths.get(sourceUri)

   String swLabelName;
   private String trimTSystem;
   private String targetSystem;
   private String relPath;

   public String getRelPath() {
      trimTSystem = scriptLocation.getParent();
      relPath = trimTSystem.replaceAll("src","check")
      return relPath;
   }

   public String getTargetSystem(){
      swLabelName = swLabelName.replaceAll("td","sw")
      new File(swLabelName).eachFileRecurse() {
         trimTSystem = it.getName();
      }
      switch(trimTSystem) {
         case ~/TG25P(.*)/: 
            targetSystem = "g2.5";
            break;
         case ~/G3T(.*)/:
            targetSystem = "G3";
            break;
         case ~/a2iR(.*)/:
            targetSystem = "a2i";
            break;
         case ~/SA2E(.*)/:
            targetSystem = "a2-e";
            break;
         case ~/a3H(.*)/:
            targetSystem = "a3";
            break;
         default:
            break;
      }
      return targetSystem;
   }   
}