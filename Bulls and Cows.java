public class Solution {
    public String getHint(String secret, String guess) {
        int bull = 0, cow = 0;
        
        int[] sarr = new int[10];
        int[] garr = new int[10];
        
        for(int i = 0; i < secret.length(); i++){
            if(secret.charAt(i) != guess.charAt(i)){
                sarr[secret.charAt(i)-'0']++;
                garr[guess.charAt(i)-'0']++;
            }else{
                bull++;
            }
        }
        
        for(int i = 0; i <= 9; i++){
            cow += Math.min(sarr[i], garr[i]);
        }
        
        return (bull + "A" + cow + "B");
    }
}
