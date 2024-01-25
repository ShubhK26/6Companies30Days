//     the basic intution of this code is to sort the array
//     and for each element we must search the first index where the condition age[y]>0.5*age[x]+7 is satisified 
//     after that we just need to subtract the index of the array and the starting index to get no of pairs
//     but there is  a trick here as array contains duplicate elements if we calculate ans for ages=[16,16]
//     we will get wrong ans 
//     so we maintain hashmap named index which contains last index of the element
//     we also maintain an hashmap named count which contains count of each element
//     we must now just iterate through keys of hashmap named index and search the first index satisfiying the given
//     condition and keep the count of no of pairs
class Solution {
    private int solve(int ages[],int start,int end,int num){
        int flag=-1;
        while(start<=end){
            int mid=start+(end-start)/2;
            if(ages[mid]>(.5*num+7)){
                flag=1;
                end=mid-1;
            }
            else{
                start=mid+1;
            }
        }
//         this flag is the check whether there is any element which satisfies the conndition 
//          if no such element is found we return -1 
        if(flag==-1){
            return -1;
        }
        return start;
    }
    public int numFriendRequests(int[] ages) {
//         to apply binary search we are sorting 
        Arrays.sort(ages); 
        int c=0;
        HashMap<Integer,Integer>index=new HashMap<>();
        HashMap<Integer,Integer>count=new HashMap<>();
//         storing the count of each element as well as the last index of each element
        for(int i=0;i<ages.length;i++){
            index.put(ages[i],i);
            count.put(ages[i],count.getOrDefault(ages[i],0)+1);
        }
// iterating through the hashmap keys and find the first index where the condition age[y]>0.5*age[x]+7 is satisfied
//  the function will returen index subtract start current index from start index  add 1 to include that index and add the final result to c
        for(int x:index.keySet()){
            int k=solve(ages,0,index.get(x)-1,x);
          
            if(k!=-1){
            c+=((index.get(x)-k)*count.get(x));
            }
          
        }
        return c;
    }
}
// Pls do Upvote if you like :)
