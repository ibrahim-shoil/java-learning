class Solution {
    public int lengthOfLastWord(String s) {
               s = s.strip(); // remove leading and trailing spaces
        char[] chars = s.toCharArray();
        int index = s.lastIndexOf(' ') + 1;
        char[] newArr = new char[ chars.length - index];
        for (int i = 0; i <  chars.length - index; i++) {
            newArr[i] = chars[index + i];
        }
        return newArr.length;
    }
}