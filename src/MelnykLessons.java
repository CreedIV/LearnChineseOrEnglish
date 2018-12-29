
public class MelnykLessons extends LanguageLesson {
    

    MelnykLessons(int lessonNum){
    	switch(lessonNum) {
	    	case 1:
	    	    lessonText = "This lesson's topic is saying hello in chinese. We will learn how to greet people in chinese. "
	    				+ "But first, let's learn some words. Just listen and repeat.  "
	    	    		+ "(ni3 ha3o) "
	    	    		+ "it means, Hello, Hi, Or how are you ? It's used to greet anyone, at any time of the day. "
	    	    		+ "You can greet people you already know or you can greet someone you meet for the first time. "
	    	    		+ "So if somebody's saying to you (ni3 ha3o), you should also respond (ni3 ha3o). "
	    	    		+ "(Ni3 ha3o ma) "
	    	    		+ "is another way of saying hello in chinese. It's basically the same as (ni3 ha3o) except for some occasions "
	    	    		+ "when (ni3 ha3o ma) is used to greet someone you have not seen for a long time."
	    	    		+ " We can translate this into english like: Hey, long time no see. How are you doing ? Here "
	    	    		+ "(ma)"
	    	    		+ " is a question particle. "
	    	    		+ "This particle is added to the end of a sentence. When you want to make a question, for example, "
	    	    		+ "(ha3o) "
	    	    		+ "means good. (Ha3o ma) means,  good? Is it  good? "
	    	    		+ "It's a question. Next word. (ha3o jiu3 bu2 jia4n). And it means long time no see.  "
	    	    		+ "(he3n)"
	    	    		+ " It means very. "
	    	    		+ "(he3n ha3o)"
	    	    		+ ", very good.  "
	    	    		+ "(ye3)"
	    	    		+ ", also. "
	    	    		+ "(ni3 ne) "
	    	    		+ "and you ? "
	    	    		+ "(Ne)"
	    	    		+ " is a particle used in elliptical questions ? (Ha3o) means good. "
	    	    		+ "(Ni3)"
	    	    		+ " means you. "
	    	    		+ "(wo3)"
	    	    		+ " means i or me. "
	    	    		+ "(xie4 xie4)"
	    	    		+ " Thank you. Please pay attention to my pronunciation."
	    	    		+ " And the last word "
	    	    		+ "(zai4jia4n)"
	    	    		+ " which means goodbye. It literally means see you again. "
	    	    		+ "(zai4)"
	    	    		+ " means again. "
	    	    		+ "(jia4n) "
	    	    		+ "means to see you.";
	            lessonWords.add(new String[] { "ni3", "you"});
	            lessonWords.add(new String[] {"ha3o", "good"});
	            lessonWords.add(new String[] {"ni3 ha3o", "hello"});
	            lessonWords.add(new String[] {"ni3 ha3o ma", "how are you"});
	            lessonWords.add(new String[] {"ha3o ma", "is it good"});
	            lessonWords.add(new String[] {"ha3o jiu3 bu2 jia4n", "long time no see"});
	            lessonWords.add(new String[] {"he3n", "very"});
	            lessonWords.add(new String[] {"he3n ha3o", "very good"});
	            lessonWords.add(new String[] {"ye3", "also"});
	            lessonWords.add(new String[] {"ni3 ne", "and you"});
	            lessonWords.add(new String[] {"wo3", "I"});
	            lessonWords.add(new String[] {"xie4 xie4", "thanks"});
	            lessonWords.add(new String[] {"zai4jia4n", "goodbye"});
	            
	            
	            lessonDialog1.add(new String[] {"ni3 ha3o", "hello"});
	            lessonDialog1.add(new String[] {"ni3 ha3o", "hello"});
	            
	            lessonDialog2.add(new String[] {"ha3o jiu3 bu2 jia4n, ni3 ha3o ma", "long time no see, how are you"});
	            lessonDialog2.add(new String[] {"he3n ha3o xie4 xie4 ni3 ne", "very good, thanks, and you"});
	            lessonDialog2.add(new String[] {"wo3, ye3 he3n ha3o", "I'm also very good"});
	            lessonDialog2.add(new String[] {"zia4 jia4n", "goodbye"});
	            lessonDialog2.add(new String[] {"ha3o za4i jia4n", "ok, goodbye"});
	            break;
	    	case 3:
	            lessonWords.add(new String[] { "pe2ngyou", "friend"});
	            lessonWords.add(new String[] { "na2n", "boy"});
	            lessonWords.add(new String[] { "nu3", "girl"});
	            lessonWords.add(new String[] { "na2npe2ngyou", "boyfriend"});
	            lessonWords.add(new String[] { "nu3pe2ngyou", "girlfriend"});
	            lessonWords.add(new String[] { "shi4", "is"});
	            lessonWords.add(new String[] { "qu4", "to go"});
	            lessonWords.add(new String[] { "yi1 qi3", "together"});
	            lessonWords.add(new String[] { "ma2ng", "busy"});
	            lessonWords.add(new String[] { "ta1", "he"});
	            lessonWords.add(new String[] { "ta1men", "they"});
	            lessonWords.add(new String[] { "wo3men", "us"});
	            lessonWords.add(new String[] { "ni3men", "you"});
	            lessonWords.add(new String[] { "do1u", "all"});
	            lessonWords.add(new String[] { "ya4o", "want"});
	            lessonWords.add(new String[] { "cha2", "tea"});
	            lessonWords.add(new String[] { "he1", "to drink"});
	            lessonWords.add(new String[] { "chi1", "to eat"});
	            lessonWords.add(new String[] { "chi1fa4n", "to eat a meal"});
	            lessonWords.add(new String[] { "fa4n", "rice"});
	            lessonWords.add(new String[] { "ka1fe1i", "coffee"});

	            lessonPhrases.add(new String[] {"wo3 ya4o", "I want"});
	            lessonPhrases.add(new String[] {"wo3 bu3 ya4o", "I don't want"});
	            lessonPhrases.add(new String[] {"wo3 ya4o chi1fa4n", "I want to eat"});
	            lessonPhrases.add(new String[] {"wo3 ya4o he1 cha2", "I want to drink tea"});
	            lessonPhrases.add(new String[] {"ni3 ma2ng ma", "Are you busy?"});
	            lessonPhrases.add(new String[] {"yi1qi3 qu4", "to go together"});
	            lessonPhrases.add(new String[] {"wo3men yi1qi3 qu4 chi1fa4n", "We go eat together"});
	            
	            
	            lessonDialog1.add(new String[] {"ni3 ha3o", "hello"});
	            lessonDialog1.add(new String[] {"ni3 ha3o", "hello"});	            
	            lessonDialog1.add(new String[] {"ni3 ma2ng ma", "Are you busy?"});
	            lessonDialog1.add(new String[] {"wo3 bu4 ma2ng, ni3 ma", "I'm not busy, how about you?"});
	            lessonDialog1.add(new String[] {"wo3 ye3 bu4 ma2ng", "I'm also not busy"});
	            lessonDialog1.add(new String[] {"wo3men yi1qi3 qu4 chi1fa4n", "Let us go eat together"});
	            lessonDialog1.add(new String[] {"ni3", "ok"});
	            lessonDialog1.add(new String[] {"ni3", "ok"});


	            lessonDialog2.add(new String[] {"wo3, ye3 he3n ha3o", "I'm also very good"});
	            lessonDialog2.add(new String[] {"zia4 jia4n", "goodbye"});
	            lessonDialog2.add(new String[] {"ha3o za4i jia4n", "ok, goodbye"});
	            break;
	    	case 5:
	    		lessonWords.add(new String[] { "ke3yi3", "may"});
	    		lessonWords.add(new String[] { "qi3ng", "please"});
	    		lessonWords.add(new String[] { "we4n", "ask"});
	    		lessonWords.add(new String[] { "we4nti3", "question"});
	    		lessonWords.add(new String[] { "we4n we4nti3", "ask question"});
	    		lessonWords.add(new String[] { "qi3ng we4n", "please ask"});
	    		lessonWords.add(new String[] { "mi2ngzi4", "name"});
	    		lessonWords.add(new String[] { "xi4ng", "surname"});
	    		lessonWords.add(new String[] { "jia4o", "to call"});
	    		lessonWords.add(new String[] { "gui4 xi4ng", "valuable surname"});
	    		lessonWords.add(new String[] { "co2ng", "from"});
	    		lessonWords.add(new String[] { "na3li3", "where"});
	    		lessonWords.add(new String[] { "jia1nna2da4", "canada"});
	    		lessonWords.add(new String[] { "ga1o xi4ng", "pleased"});
	    		lessonWords.add(new String[] { "re2nshi", "to know"});
	    		lessonWords.add(new String[] { "xia1nsheng", "mister"});
	    		lessonWords.add(new String[] { "wo3 de", "my"});
	    		lessonWords.add(new String[] { "ni3 de", "your"});
	    		lessonWords.add(new String[] { "ta1 de", "his"});
	    		lessonWords.add(new String[] { "xue2sheng", "student"});
	    		lessonWords.add(new String[] { "xue2xi2", "study"});
	    		lessonWords.add(new String[] { "zuo4", "sit"});
	    		lessonWords.add(new String[] { "nu3shi4", "miss"});
	    		lessonWords.add(new String[] { "na3li3", "where"});
	    		lessonWords.add(new String[] { "jia1", "home"});
	    		lessonWords.add(new String[] { "za4i", "at"});
	    		lessonWords.add(new String[] { "bu2yo4ng xie4", "you're welcome"});
	    		lessonWords.add(new String[] { "bu2 xie4", "you're welcome"});
	    		lessonWords.add(new String[] { "dui4bu4qi3", "sorry"});
	    		lessonWords.add(new String[] { "wa3n", "late"});
	    		lessonWords.add(new String[] { "me2igua1nxi4", "no problem"});
        
    	}
    }
}
