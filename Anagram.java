import java.util.*;
import java.lang.*;
import java.io.*;

class nodeCollector{
    ArrayList<node> collector;
    boolean isEmpty;
    public nodeCollector(){
        collector = new ArrayList<node>();
        updateSize();
    }
    public boolean updateSize(){
        if(collector.size()!= 0){
            isEmpty = true;
        }else{
            isEmpty = false;
        }
        return isEmpty;
    }
    public ArrayList<node> getCollector(){
        return collector;
    }
    public boolean condition(){
        return isEmpty;
    }

    public static class pair{
        private long a;
        private long b;
        public pair(long first, long second){
            a = first;
            b = second;
        }
        public void repr(){
            System.out.println("First value is "+a);
            System.out.println("Second value is "+b);
        }
    }
}

class node{
    int[] blueprint= new int[38];
    String word;
    public String fullView = "";
    public node(String inputStr){
        word = inputStr;
        // To know how many character in the string
        if(validator(word)){
            int n = word.length();
            for(int i=0;i<n;i++){
                int asciiValue = (int)word.charAt(i);
                if(asciiValue == 39){
                    // '
                    blueprint[1]++;
                }else if(asciiValue>=97){
                    // a-z
                    blueprint[asciiValue-85]++;
                }else if(asciiValue >=48 && asciiValue <=57){
                    // Numbers
                    blueprint[asciiValue-46]++;
                }else{
                    // Space
                    blueprint[0]++;
                }
            }
        }
    }
    public String getFullView(){
        return fullView;
    }
    public String getWord(){
        return word;
    }
    public void setWord(String newWord){
        word = newWord;
    }
    public boolean validator(String checker){
        int n = checker.length();
        for(int i=0;i<n;i++){
            int asciiValue = (int)checker.charAt(i);
            if(!((asciiValue==39)|| (asciiValue>=97 && asciiValue <=122)||(asciiValue>=48 && asciiValue <=57)||(asciiValue ==32))){
                return false;
            }
        }
        return true;
    }
    public int primaryHashing(){
        int hashValue = 0;
        for(int i=0;i<38;i++){
            hashValue = hashValue + (int)(i*blueprint[i]);
        }
        return hashValue;
    }
}
class stringObj{
    int frequency;
    String key;
    public stringObj(String inputStr){
        key = inputStr;
        frequency =1;
    }
    public void incrementFreq(){
        frequency++;
    }
}

// Main Class Here !!!!
public class Anagram{
    private static class timeCalculator{
        private long diff;
        private long start;
        private long end;

        public timeCalculator(){
            diff = 0;
            start = 0;
            end = 0;
        }
        public void startTime(){
            start = System.currentTimeMillis();
        }
        public void endTime(){
            end = System.currentTimeMillis();
        }
        public long diffTime(){
            return end - start;
        }
    }

    private static timeCalculator time1 = new timeCalculator();
    private static timeCalculator time2 = new timeCalculator();
    private static timeCalculator time3 = new timeCalculator();

    private int sizeOfVocab;
    private int inputSize;
    private int count4 =0 ;
    private ArrayList<nodeCollector>[] table =  new ArrayList[10];
    public Anagram(){
        for (int i = 0; i < 10; i++) {
            table[i] = new ArrayList<nodeCollector>(500);
            for (int j = 0; j < 500; j++) {
                // Initialise things to avoid nullPointerException
                table[i].add(new nodeCollector());
            }
        }
    }

    public boolean validator(String checker){
        int n = checker.length();
        for(int i=0;i<n;i++){
            int asciiValue = (int)checker.charAt(i);
            if(!((asciiValue==39)|| (asciiValue>=97 && asciiValue <=122)||(asciiValue>=48 && asciiValue <=57)||(asciiValue == 32))){
                return false;
            }
        }
        return true;
    }
    public boolean compareTo(node a, node b){
        Random rand = new Random();
        int k = rand.nextInt(5);
        for(int i=0;i<38;i++){
            if(a.blueprint[i] != b.blueprint[i]){
                return false;
            }
        }
        return true;
    }
    public node compareToDiffer(node a, node b){
        node answer = new node("");
        int i;
        for(i=1;i<38;i++){
            answer.blueprint[i] = (int)(b.blueprint[i]-a.blueprint[i]);
            if(40*answer.blueprint[i]<0){
                return null;
            }
        }
        for(int j=0;j<38;j++){
            if(j==0){
                // This case will never appear in our working but just to be exhaustive
                continue;
            }
            else if(j ==1){
                for(int k=0;k<answer.blueprint[j];k++){
                    answer.word += (char)(39);
                }
            }else if(j>=2 && j<=11){
                for(int k=0;k<answer.blueprint[j];k++){
                    answer.word += (char)(i+46);
                }
            }else{
                for(int k=0;k<answer.blueprint[j];k++){
                    answer.word += (char)(i+85);
                }
            }
        }
        // Used to find suffix between words just to make permutations easy
        return answer;
    }
    private void computeAnagrams(String word){
        Vector<String> answer = new Vector<String>();
        int n = word.length();
        if(n<3 || n>12|| !validator(word)){
            System.out.println("-1");
            return;
        }
        node newNode = new node(word);
        club(newNode,n,answer);
        Set<String> hash_set = new HashSet<String>();
        for(int i=0;i<answer.size();i++){
            hash_set.add(answer.get(i));
        }
        answer.clear();
        int sizeOfSet = hash_set.size();
        String[] newArr = new String[sizeOfSet];
        int j=0;
        for(String str: hash_set){
            newArr[j] = str;
            j++;
        }
        Arrays.sort(newArr);
        for(int i=0;i< newArr.length;i++){
            String[] individualWords = newArr[i].split(" ");
            String curr;
            int globalCount=1;
            for(int k=0;k< individualWords.length;k++){
                curr = individualWords[k];
                node currentNode = new node(curr);
                nodeCollector dm = table[curr.length()-3].get(currentNode.primaryHashing());
                int count = 0;
                for(int r=0;r<dm.collector.size();r++){
                    if(dm.collector.get(r).word.equals(curr)){
                        count++;
                    }
                }
                globalCount *= count;
            }
            for(int glob=0;glob<globalCount;glob++) {
            System.out.println(newArr[i]);
            }
        }
        System.out.println("-1");
    }

    private int club(node newNode,int t, Vector<String>answer){
        first(newNode,t,answer);
        second(newNode,t,answer);
        third(newNode,t,answer);
        Random  rand = new Random();
        return (rand.nextInt())%2;
    }

    public int first(node newNode, int n, Vector<String> answer){
        if(n<3|| n>12){
            return -1;
        }else{
            nodeCollector dm = table[n-3].get(newNode.primaryHashing());
            int totalSize = dm.collector.size();
            for(int i=0;i<totalSize;i++){
                node mightBeAnagram = dm.collector.get(i);
                if(compareTo(newNode, mightBeAnagram)){
                    answer.add(mightBeAnagram.getWord());
                }
            }
            return 1;
        }
    }

    public int second(node newNode, int n, Vector<String> answer){
        if(n > 5 & n<13){
            nodeCollector dm;
            int number = 3;
            while(2*number <= n){
                for(int c=0;c<500;c++){
                    dm = table[number-3].get(c);
                    int totalSize = dm.collector.size();
                    for(int i=0;i<totalSize;i++){
                        node suff = dm.collector.get(i);
                        node crop = compareToDiffer(suff, newNode);
                        if(crop != null){
                            int index = answer.size();
                            first(crop,n-number,answer);
                            int finale = answer.size();
                            while(index < finale){
                                answer.add(answer.get(index)+" "+dm.collector.get(i).word);
                                answer.set(index, dm.collector.get(i).word+" "+answer.get(index));
                                index++;
                            }
                        }
                    }
                    }
                number++;
            }
            return 1;
            }else{
                return -1;
        }
    }
    public int third(node newNode, int n, Vector<String>answer){
        if(n>8 && n<13){
            int number=3;
            nodeCollector dm;
            // Change a little
            while(number <= n-6){
                for(int c=0; c < 500; c++){
                    dm = table[number-3].get(c);
                    int totalSize = dm.collector.size();
                    for(int i=0;i<totalSize;i++){
                        node suff = dm.collector.get(i);
                        node crop = compareToDiffer(suff, newNode);
                        if(crop != null){
                            int index = answer.size();
                            second(crop,n-number,answer);
                            int finale = answer.size();
                            while(index < finale){
                                answer.set(index, dm.collector.get(i).word+ " " +  answer.get(index));
                                index++;
                            }
                        }
                    }
                }
                number++;
            }
            return 1;
        }else{
            return -1;
        }
    }

    public static void main(String args[]){
// First Intialise Anagram class then do table insertion and anagram finding
        if(args.length ==2) {
            time2.startTime();
            File vocabularyFile = null;
            File inputFile = null;
            Scanner inputTXT = null;
            Scanner vocabTXT = null;
            try {
                vocabularyFile = new File(args[0]);
                inputFile = new File(args[1]);
                inputTXT = new Scanner(inputFile);
                vocabTXT = new Scanner(vocabularyFile);
            } catch(Exception e) {
                System.out.println("Format not matched!!!" + e);
                return;
            }
            if(vocabularyFile != null && inputFile != null && inputTXT != null && vocabTXT != null) {
                Anagram result = new Anagram();
                result.sizeOfVocab = vocabTXT.nextInt();
                vocabTXT.nextLine();
                int count1=0;
                int count2=0;
                while(vocabTXT.hasNext()){
                    String quarantine = vocabTXT.nextLine();
                    int size = quarantine.length();
                    if(size<3 || size >12 || !result.validator(quarantine)){
                        count2++;
                    }else{
                        count1++;
                        node newNode = new node(quarantine);
                        result.table[size-3].get(newNode.primaryHashing()).collector.add(newNode);
                    }
                }
                time3.startTime();
                result.inputSize = inputTXT.nextInt();
                String inputWord;
                inputTXT.nextLine();
                while (inputTXT.hasNext()) {
                    inputWord = inputTXT.nextLine();
                    // Search for anagrams
                    result.computeAnagrams(inputWord);
                }
                time2.endTime();
//                System.out.println(count1);
//                System.out.println(result.count4);
            }
            time3.endTime();
            nodeCollector.pair TimePair = new nodeCollector.pair(time2.diffTime(), time2.diffTime());
//            System.out.println(time3.diffTime());
//            System.out.println(time2.diffTime());
        }
        else{
            if(args.length >2){
                System.out.println("More than required arguments provided!!!");
            }
            if(args.length <2){
                System.out.println("Less than required arguments provided!!!");
            }
        }
    }
}