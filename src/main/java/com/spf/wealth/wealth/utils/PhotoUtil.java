package com.spf.wealth.wealth.utils;

import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class PhotoUtil {

    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream("E:\\GitHub\\wealth\\src\\main\\resources\\photo\\"+path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String str = "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAA8AMADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD3lLWFUVTGjEDBJUc077PB/wA8Y/8AvkUl1dQWVrJc3UyQwRKWeSRsKoHcmvLj8ZvDNnqt05fVb6NjiNooAseMk8BpB7DO0fd9znOdRRaRpChWqv8AdQb7+R6l9ng/54x/98ij7PB/zxj/AO+RWHoXjXw/4h043tlqMSohVZUnPlvGxGQpB/HkZBwcE4roK08yJRcZOMlZoj+zwf8APGP/AL5FH2eD/njH/wB8ipKKBEf2eD/njH/3yKZJHaxRtJIkKRoCzMwACgdST6VPUdxBHdW0tvMu6KVCjrkjKkYI4oGrX12IoPsV1Cs1v9nmib7rx4ZT24Iqqb6x/tgaYtuJJtoZ2RVIjBBI3c5HT0x8y+oq7a2sNlbiCBWEYLN8zliSxJJJJJJJJPNUNVn1mK+09NNtYprZ5MXTuBlF3IOMuuPlMh4DcqBjmhtI0jGMpuMdtbX0+80fs8H/ADxj/wC+RR9ng/54x/8AfIqSigyI/s8H/PGP/vkVlaDePq1i893pBsJFfaI5FbJG1Tn5kU8ElemMqcE1s0VSa5WmtRa3Kaaei3rzkqUYYEe3gdPfHY9u5qx9ng/54x/98iljlSVdyEkfQin1LHzcyuR/Z4P+eMf/AHyKPs8H/PGP/vkVi+L/ABfpXgnQ21XVmkMW8RpFCAZJGPZQSAeMnr0BpfCPiyw8aaAmsadFcxW7yNGFuFVXypweFJH60LW9ugPTc0JrR3uYvKWFIV5f5QS3I4xj0z3HX25s/Z4P+eMf/fIqSipUbNvuBC9rC64Ear7qozSrawKoXykOBjJUZNS1Xv0upNOukspFiu2icQSOOFfB2k8HgHHY07a3E9NST7PB/wA8Y/8AvkVHNBD5ZURIGb5RhRkZ7/h1/Cs/QP7aWGWLWQrOjHy5QVJcbmxyuAcLs52p34rUbm4jB6AM348D+ppijLmVznvHHhWXxjoS6XHqRsE85ZJGEPmbwAcLjcO5B/AVa8O+GNP8O+HotIhhhkTy9tw/lAfaGxhmYc5z6HPHFTjVbj+20sm0+X7NLGHjuk3MvIJw3y7Vxtx94n5l45OOW+JXjtPDOmGw0+YPrd18sUcZy0QP8ZGD9AD1z7VnJxgnLuddF1a9qEHon/TPOPBekRXXxiv7XTVkbRoZrjzVhkZU8vDqoJBGRuYAf4Zr6Erz34V+DZtA0uXVdWib+2b8lpDLzJGhOdpOTyT8x6HnB6V6FTpRcKai+hpmFWNWu3HppfvYK47xZ41bRLpLOwSGa5HzS+YCQg7Dgjnv/wDrq/41t4bvw99nmvrW0D3EJAupESOYq4byyXVhyFJA2nkDIIyD57ovh6TV9M1CV3LiygWOCbJ2HYMYB642jpj05HfhzGrVjFQovV3fyR4GZV6kYKlQ+OX5LV/1Y9Su7nUDoSXOnwpLeOsbBCARgld2AWXOFLEAsOnWrVi9zJp9s97Gsd00SmZE6K+BuA5PAOe5rk/APiCO8sYtIaNhcW8TMGAG0oCB65z83piu0rvp1VUpqUdnqdOFn7WjCt3X/D/ieaeK/Bt9Nf6lrTXdnDZqpmcuz7lRVySQFPYHpmudn8L3EOhNrH220a1DtGARKjl1YoV2ugIIZSMHHSvba86+IEVjpGj2um6bb29nHNMZpILaMRq2BjJCjB7fl3xx42OwNCnTlV6/qz6/K81xNWrTw99NFt0SMn4cW8sniUzohMcMLb29M8CvW64L4YWmywvbtosGSQIrkdQByAc+p9PxPbva7cup8mGj56nDntX2mNku1keJ6T8S/iF4lv7+Dw/oej3iWj4YtlCFJIUndMAc47V0mk6z8V5tXtI9U8M6VBYNKouJY5FLImeSP3x5x7GuA1i+1T4ffGHVZNGtI9Su9SQtFE4eRv3hDEYXBJ3L09Mc966qf4i+O/C8sN14v8KW6aVIdrS2JyyHI5J8xx34B259eK3pySUW2/PtfsfPt+9JNv5dj1tUVBhFCj0AxRJIkUbSSOqRoCzMxwAB1JNc34E/siXwxDd6JPNLZ3TeZ++VVdGCqhUhQBkbOeuTk5Oc0vxCubyz+HuvXNhIYrmOykZZA+0rxyQcHnGcfzHUdFa0b8rudEFGTSWx85fErxBqXxG1DVNaslI8OaHtihZsgMXcLuA7sx59lUd+vsnwD/5Jbbf9fU3/AKFXj3gTxz4T8PeAtZ0HVrPU2u9WDpNPZwx5EZXao3M/bLH7vfvUfww+JFh4O0rWtK1VL8WuoJ8k+nBfPicqVJDMwAwOQcHmiCUbx8v+HFJuVn5n1nXF/ELxnP4YtbKy0uGO41rUpRDaRvyq8gFiPqQB7n2NTeBrrUhp/wBj1AzXKEG5tbtkl+aJ8Oqs0jNlh5m0fO3CkH7u5ub+OGmyyeG7HWo3UjS7lXaGQ/I4YgdMcnOO44JqcRF09H5fcTz3g5Q7FrxFqvibwDZ6brF9q761Yllg1GGS2ij2Fv8AlpGUUEYxjDZ6+/HolvPFdW0VxA4khlQOjqeGUjINcfa3mpeMLERXFkB4d1fTztmiCrLCWReG3Oec+ZwEIHycnLbec+DuqT2Nxq/gq7CNJpMrmOSMEqRvIYEk88kY4HeqlBwnyNp3V1b8V8tyFLaS2en+X3nqcwlZAImCtnqf/wBVZeh6Fa+H4/slo8jxvucmQKDnCL/CoHRRzjJOSSc1sVHNwgk/55nd+Hf9M0ldX8zVxTab6FFtLgmv4bvc6XFqdoZNvzLzhTkHjDEcY6+wI8ZTwl8QdL8d3niK10G0vZmnlaM3FxHImCTgrucMMDGM4IFe6L/x8v8A7i/zapKzdJOXNsdWHxMqCkkk+be55RB4o+LFzLLHD4a0Z3iOJAJR8pyRz+/9VP8AkivRtCuNTutGt5dZshZ6gVxPErKVDeq4ZuPxzVDwtq82tx3t4XJtvN2QqxQleN38PT5XQYJJBBzjpWve39vp8IluXZEJwCEZugJPAB7An8KcYypp+0un5irSb9xxjddYp/q2Zfi7SJdZ0JobaJZLmNxJCGcrg4KnBzjJVmHPHNc1p8HjPTdIGmRaJZvbgMp3yLuYHOckSD1r0KiuerhI1JufM02raW2+48utgo1aqqqTi7W0t/kzyTw3e6/pGrXOl2FpBJOzZkt5CCFI64bcO3ua9ZjZnjVmQoxAJVsZHtxkVz114V+0+K4dc+27fLKnyfKznAx97P8AStDUtEttUvbG6neRXs33xhQuCdytzkEjlB0IOMjvSy+jOlTcKrejduunT7+xnleElQlOnUk1G+mz032Xn/wxfnnjt4jJISFBA4Usck4HA56mvGPGOpR6r4lnmhYPEoWONlJOQB79OSeK9qZVdGR1DKwwVIyCKzzoGjMSTpNgSTkk2yc/pSx2FliYKKdrO59JleOp4Ko6k4tu1hvh6yOn+HrG1YMHSIbgygEE8kYHua06r3N3b6fArzEpH90bULYwCegHQAH8qsV1xcV7q6Hm1KntKkpPdu/3nKeMPiBpfgmWzTU7W+kW7DbHt41ZRtxnOWHqK2dTg03W/DtxHdmGfTbm3LMzPhChGd24dB3yOnWn6zo1hr+lTabqUAntZhhlPBB7EHsR615h/wAKSvLWO5tNK8bahZ6bOTutfKJDAjB37ZFVuOPuiolzNONrmbclK61RR+AmoziXXNH3mWziZZomRf3YYkqTk4b5gFwCP4T07+z3NtBeW0ltdQxzwSqUkilQMrqeoIPBFc34L8B6T4Is5I7HzJrmbHnXM2NzY7DHRc5OPzJrqa1S91Jk0ouMdTn/APhBPB//AEKmh/8Aguh/+JryKf4T6npXxeGpWHhqzvPC0soZ4AYJFRGGGXy5iMENk/L0B49K9yu47157c20qpErfvQcfMMj2Pbd3HUVNc3MVpAZpiwQEL8qliSSAAAASckiknZ83Y15XJcq6jooo4IkiiRY40UKiIMBQOgA7CszxLoVt4l8PXmk3SgpPGQpJI2uOVbj0OD+FWLjUCthDd2kDXSSgMoUMPlKkg8AnngdO/ar1OUeaN3sws4nnHwx1e6083PgfWyRq2lZ8ogDZLBxtKHgnGe46Ee+MvwfA1v8AHLxUtnzZmLdMYiXQSEqcMzchs7zgd8joK7/XdK0XXrm2sNVsRcSIRLDIGKPEeTlXUh1+529s1PpHhzSdCkuZdPtSk90++eeSV5ZZD/tO5LEe2cVKjLmUpdE/mZOm1FR6XTL95aQX9lPZ3Kb7e4jaKVMkblYYIyORwe1ef+DtVOj6tqXhe8eSWxjndrKdbQIg3zuhjyoHG4gbgiqG3qDgLn0asHVrWxXVLZ28PW17JdMFmuGtwzIAUXk7D2OeSBhDzVxi5OyN0nJOK3/A2l5uJCOgCr+PJ/qKkrldbtRrunNY3EskUTOWfysZfgjB3AggZyOMgqpGCBWjFfPBCkMMMMcUahURFwFA4AA7Cm42imDjaKl3Lmp6ZDqtssE7Oqq24FNuehHcHsTVi4tbe7jEdzBFMgOQsiBgD681nf2nP/dj/I/40f2nP/dj/I/41FkZqKT5lua1FZP9pz/3Y/yP+NNk1CWWJ42VQrKVJUsp59CDkH3FMZo2t5a30RltLmG4jDbS0ThwD6ZHfkVPWBYyJptstvaQRxxjsSzE8ADJJJOAABnoAB0Aqz/ac/8Adj/I/wCNVLlv7uwle2prUVk/2nP/AHY/yP8AjR/ac/8Adj/I/wCNSM0Z7eC5QJPDHKoOQsihhn15rPg8P2tv4judcSSU3NxH5bqQm0DCDqF3f8s14JIGTgc0n9pz/wB2P8j/AI0f2nP/AHY/yP8AjVQk4XcdL6P0JcYvVjNZMI1HSxJrUVgxlwkDy7Tcnch2gbhuPG3GG+/06Vs1zVzDb3mp2+o3FuJLi3XbHmSTYBuDfc3bSdwU5IzlVPVRi/8A2nP/AHY/yP8AjQ+Wytv1BQim5LdjfEnhuz8T6fHZXskscaSeYDEEJzsZP41YdHPOMg4IIIrYrJ/tOf8Aux/kf8aP7Tn/ALsf5H/GpKsa1RzQRXMTRTxJLG3VHUMD36Gs3+05/wC7H+R/xo/tOf8Aux/kf8aNxptO6NRESKNY41VEUBVVRgADoAKqjUoW1Q6eiu8qqWcjGE4B55z/ABL0Hf2OKEt7NK8bbiuw5ARiAeQeeeemPxNS/wBpz/3Y/wAj/jVJJIznzSejtqLea/a2et2ekeXLLeXWCEjKjYvzHcdzAkYR/u5xj1K51ayf7Tn/ALsf5H/GoZ7uS4KbwmFOduOD9f8APc0kXCLb1ZuUVk/2nP8A3Y/yP+NH9pz/AN2P8j/jSA//2Q==";
        generateImage(str,"E:\\GitHub\\wealth\\src\\main\\resources\\photo\\"+System.currentTimeMillis()+".jpeg");
    }

}
