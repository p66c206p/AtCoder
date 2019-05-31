import java.util.*;

public class Main {
    static int n;
    public static void main(String[] args) {
    }
    
    // ここだと(public) static class ※要static
    public static class Hoge {
    }
    
    // 複数個置ける
    static class Hoge2 {
    }
}

// ここだとclass ※public, static不要
class Hoge {
}

// 複数個置ける
class Hoge2 {
}
