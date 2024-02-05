class 산모양타일링 {
    public int solution(int n, int[] tops) {        
        int mod = 10007;

        int [][] dp = new int[n + 1][2];
        
        dp[0][0] = 1;
        
        for(int idx = 0; idx < n; idx++){
            if(tops[idx] == 1){
                dp[idx + 1][0] = dp[idx][0] * 3 + dp[idx][1] * 2;
            } else {
                dp[idx + 1][0] = dp[idx][0] * 2 + dp[idx][1];
            }
            
            dp[idx + 1][1] = dp[idx][0] + dp[idx][1];
            
            dp[idx + 1][0] %= mod;
            dp[idx + 1][1] %= mod;
        }
        
        return (dp[n][0] + dp[n][1]) % mod;
    }
}