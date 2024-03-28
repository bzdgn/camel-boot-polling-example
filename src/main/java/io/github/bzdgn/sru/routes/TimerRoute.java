//package io.github.bzdgn.sru.routes;
//
//import org.apache.camel.builder.RouteBuilder;
//import org.springframework.stereotype.Component;
//import java.util.Date;
//
//@Component("TimerRoute")
//public class TimerRoute extends RouteBuilder {
//
//    public TimerRoute() {
//    }
//
//    @Override
//    public void configure() throws Exception {
////        from("quartz://myGroupName/myTimerName?cron=0/3+*+*+*+*+?")
//        from("timer:triggerTimer?repeatCount=1&delay=1000")
//                .routeId("triggerTimer")
//                .to("direct:sru-main-route");
//    }
//
//}
