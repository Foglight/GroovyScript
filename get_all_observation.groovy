ts=server.TopologyService
def sb=new StringBuilder("Cartridge||Type||Metrics||Metrics Type||Metrics Unit\n")

obsType = ts.getType("Observation")
defaultObs=ts.getType("TopologyObject").getPropertiesOfType(obsType)



ts.getTypeNames().each{name->
	type=ts.getType(name)
	observations=type.getPropertiesOfType(obsType) - defaultObs
	cartridge=type.getCartridgeName()
	if(cartridge==null) cartridge = "No Cartridge"
	observations.each{
		unit = it.getUnitName()
		unit=unit==null?"":unit
		sb << "$cartridge||$name||${it.getName()}||${it.getValueType().getName()}||$unit\n"
	}
}

return sb

