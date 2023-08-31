package com.driver;

public class CurrentAccount extends BankAccount{
    private String tradeLicenseId; //consists of Uppercase English characters only


    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000.00);
        if(balance<5000) throw new InsufficientBalanceException("Insufficient Balance");
        this.tradeLicenseId = tradeLicenseId;

    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(validId(tradeLicenseId)==false) {
            // try to create valid id
            String validLicense = createValidLicense(tradeLicenseId);
            if(validLicense==null) throw new invalidLicenseException("Valid License can not be generated");
            else {
                this.tradeLicenseId = validLicense;
            }
        }
    }

    private boolean validId(String str) {
        if(str==null || str.length()==0) return false;

        char curr = str.charAt(0);
        for(int i=1; i<str.length(); i++) {
            if(curr==str.charAt(i)) return false;
            curr = str.charAt(i);
        }
        return true;
    }

    private String createValidLicense(String s) {
        if(s==null || s.length()==0) return null;

        int maxCount = 0;
        char mostFrequentChar = 'A';
        char[] freq = new char[26];

        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            freq[ch-'A']++;
            if(maxCount<freq[ch-'A']) {
                maxCount = freq[ch-'A'];
                mostFrequentChar = ch;
            }
        }

        // check if reorganising is possible
        if(maxCount>s.length()-maxCount+1) return null;

        int k = 0;
        char ans[] = new char[s.length()];
        while(freq[mostFrequentChar-'A']>0) {
            freq[mostFrequentChar-'A']--;
            ans[k] = mostFrequentChar;
            k+=2;
        }


        for(int i=0; i<26; i++) {

            while(freq[i]-->0) {
                if(k>=ans.length) k =1;
                ans[k] = (char)('A'+i);
                k += 2;
            }
        }

        return new String(ans);
    }

}
