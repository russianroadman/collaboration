package tanto.collaboration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class CollaborativeController {

    private String text = "works";
    private ExecutorService clients = Executors.newFixedThreadPool(5);

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

    @ResponseBody
    @PostMapping("save-data")
    public void sendData(@RequestBody Text content){
        text = content.getText();
    }

    @ResponseBody
    @PostMapping("update")
    public DeferredResult<Text> updateRequest(@RequestBody Text content) {

        DeferredResult<Text> output = new DeferredResult<>();
        String clientVisibleContent = content.getText();

        clients.execute(() -> {

            while(true){

                if( clientVisibleContent.equals(this.text) ){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                } else {
                    output.setResult(new Text(this.text));
                    break;
                }

            }

        });
        return output;
    }

}
