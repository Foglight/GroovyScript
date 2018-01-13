/*Script: Service by Mbean Ref*/
import java.util.Date

import javax.management.ObjectName;
import com.quest.nitro.service.util.MBeanRef;

def sb=new StringBuilder("Cartridge||Rule||Monitored Metrics\n")
def rs = new MBeanRef(ObjectName.getInstance("com.quest.nitro:service=Rule")).ref();

rs.mRunningRules.each{ name, rule->
	observations = [] as Set
	rule.mSeverities.each{ sname, ser->
		try{
			ser.mExpressions.each{ ex->
				ex.getQueries()?.values().each{ observations << "${it.getResolvedTopologyType().getName()}.${it.getObservationName()}" }
			}
		}catch(Exception e){
		}
	}
	if(observations){
		sb << "${rule.getCartridgeName()}||${rule.getName()}||${observations.join(';')}\n"
	}
}


sb