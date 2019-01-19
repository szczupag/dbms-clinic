import React, {Component} from 'react';
import Clinic from './Clinic';
import constants from '../../constants/pages';
import Select from 'react-select';

class Clinics extends Component {
    constructor(props){
        super(props)
        let citiesMap = this.props.localizations.map((localization)=>{
            return { valueFix: localization.city, value: localization.city+'', label: localization.city}
        });
        this.state = {
            clinics: [],
            localizations: this.props.localizations,
            sortBy: null,
            cities: citiesMap,
            filtrCity: null
        }
        this.sortChangeHandler = this.sortChangeHandler.bind(this);
        this.filtrCityChangeHanler = this.filtrCityChangeHanler.bind(this);
    }

    componentDidMount () {
        this.props.getHandler(constants.CLINICS);
        this.setState({
            clinics: this.props.clinics
        })
    }

    componentWillReceiveProps(newProps){
        this.setState({
            clinics: newProps.clinics,
            localizations: newProps.localizations
        })
    }

    filtrCityChangeHanler(selectedCities){
        const initClinics = this.props.clinics;
        if(selectedCities.length > 0){
            const selectedCitiesValues = selectedCities.map(sel=>{return sel.valueFix});
            const filteredClinics = initClinics.filter(function(clinic){return selectedCitiesValues.includes((clinic.localization.city))?clinic:null});
            this.setState({
                filtrCity: selectedCities, 
                clinics: filteredClinics})
        }else{
            this.setState({
                filtrCity: null,
                clinics: initClinics
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
            <div className="clinics">
                <div className="page-title">
                    <span>Clinics</span>
                    <button 
                        className="default-btn back"
                        onClick={()=>this.props.changePanel(constants.HOME)}
                    >Back</button>
                </div>
                <div className="options">
                    <button 
                        className="controls-btn add"
                        onClick={()=>this.props.changePanel(constants.NEW_CLINIC)}
                    >Add new clinic</button>
                    
                </div>
                <div className="sort">
                    <Select
                        placeholder="Sort by"
                        className="selectBox sort"
                        options={[
                            { value: 'name', label: 'name' },
                            { value: 'type', label: 'type' },
                            { value: 'city', label: 'city' }
                        ]}
                        value={this.state.sortBy}
                        onChange={this.sortChangeHandler}
                    />
                </div>
                <div className="filters">
                    <Select
                        isMulti
                        placeholder="Filtr city"
                        className="selectBox sort"
                        value={this.state.filtrCity}
                        onChange={this.filtrCityChangeHanler}
                        options={this.state.cities}
                    />
                </div>
                <div className="elements">
                {
                    this.state.clinics.map( (clinic, index) => {
                        return <Clinic 
                            key={index} 
                            data={clinic}
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

export default Clinics;