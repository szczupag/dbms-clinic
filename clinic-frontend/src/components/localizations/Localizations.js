import React, {Component} from 'react';
import constants from '../../constants/pages';
import Localization from './Localization';
import Select from 'react-select';

class Localizations extends Component {
    constructor(props){
        super(props)
        let citiesMapFilter = this.props.localizations.map((localization)=>{
            return { valueFix: localization, value: localization.city+'', label: localization.city}
        });
        let citiesMapSort = this.props.localizations.map((localization)=>{
            return { value: localization, label: localization.city}
        });
        this.state = {
            localizations: [],
            citiesFilter: citiesMapFilter,
            citiiesSort: citiesMapSort,
            sortBy: null,
            filtrCity: null,
        }
        this.sortChangeHandler = this.sortChangeHandler.bind(this);
        this.filtrCityChangeHanler = this.filtrCityChangeHanler.bind(this);
    }

    componentDidMount () {
        this.props.getHandler(constants.LOCALIZATIONS);
        this.setState({
            localizations: this.props.localizations
        })
    }

    componentWillReceiveProps(newProps){
        this.setState({
            localizations: newProps.localizations
        })
    }

    filtrCityChangeHanler(selectedCities){
        const initLoc = this.props.localizations;
        if(selectedCities.length > 0){
            const selectedCitiesValues = selectedCities.map(sel=>{return sel.valueFix.city});
            const filteredLocs = initLoc.filter(function(loc){return selectedCitiesValues.includes((loc.city))?loc:null});
            this.setState({
                filtrCity: selectedCities, 
                localizations: filteredLocs})
        }else{
            this.setState({
                filtrCity: null,
                localizations: initLoc
            })
        }
        
    }

    sortChangeHandler(selectedSort){
        switch(selectedSort.value){
            case 'name':
                this.state.clinics.sort((a,b)=>a.name>b.name);
                break;
            case 'type':
                this.state.clinics.sort((a,b)=>a.type>b.type);
                break;
            case 'city':
                this.state.clinics.sort((a,b)=>a.localization.city>b.localization.city);
                break;
            default:
                break;
        }
        this.setState({
            sortBy: selectedSort
        })
    }

    render(){
        return(
            <div className="localizations">
                <div className="page-title">
                    <span>Localizations</span>
                    <button 
                        className="default-btn back"
                        onClick={()=>this.props.changePanel(constants.HOME)}
                    >Back</button>
                </div>
                <div className="options">
                    <button 
                        className="controls-btn add"
                        onClick={()=>this.props.changePanel(constants.NEW_LOCALIZATION)}
                    >Add new localizations</button>
                </div>
                <div className="filters">
                    <Select
                        isMulti
                        placeholder="Filtr city"
                        className="selectBox sort"
                        value={this.state.filtrCity}
                        onChange={this.filtrCityChangeHanler}
                        options={this.state.citiesFilter}
                    />
                </div>
                <div className="elements">
                {
                    this.state.localizations.map( (localization, index) => {
                        return <Localization
                            key={index} 
                            data={localization}
                            deleteHandler={this.props.deleteHandler}
                            editItemHandler={this.props.editItemHandler}
                            changePanel={this.props.changePanel}
                            />
                    })
                }
                </div>
            </div>
        )
    }
}

export default Localizations;