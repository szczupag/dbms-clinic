import React, {Component, Fragment} from 'react';
import constants from '../../constants/pages';
import Select from 'react-select';

class NewClinic extends Component {
    constructor(props){
        super(props)
        let localizationMap = this.props.localizations.map((localization)=>{
            // show localizations without clinic only            
            if (localization.clinic===undefined) {
                return { value: localization, label: localization.street+" "+localization.postalCode+" "+localization.city}
            }
        });
        let departmentsMap = this.props.departments.map((department)=>{
            // show departments without clinic only
            if(department.clinic==undefined){
                return { valueFix: department, value: department+'', label: department.name }
            }
        });
        localizationMap = localizationMap.filter(function(el){return el !== undefined;});
        departmentsMap = departmentsMap.filter(function(el){return el !== undefined;});
        this.state={
            name: '',
            type: '',
            departments: departmentsMap,
            localizations: localizationMap,
            department: null,
            localization: null,
            error: null
        }
        this.nameChangeHandler = this.nameChangeHandler.bind(this);
        this.typeChangeHandler = this.typeChangeHandler.bind(this);
        this.localizationChangeHandler = this.localizationChangeHandler.bind(this);
        this.departmentChangeHandler = this.departmentChangeHandler.bind(this);
        this.submitHandler = this.submitHandler.bind(this);
    }
    
    nameChangeHandler(e){
        this.setState({name: e.target.value})
    }

    typeChangeHandler(e){
        this.setState({type: e.target.value})
    }

    localizationChangeHandler(selectedLoc){
        this.setState({localization: selectedLoc});
    }

    departmentChangeHandler(selectedDep){
        this.setState({department: selectedDep});
    }

    submitHandler(){
        const localizationId = this.state.localization!=null ? this.state.localization.value.id : null;
        const departmentIds = this.state.department!=null ? this.state.department.map(dep=>{return dep.valueFix.id}):null;
        console.log(departmentIds)
        if( this.state.name != '' && this.state.type!='' && localizationId!=null){
            const data = {
                name: this.state.name,
                type: this.state.type,
                localizationId: localizationId,
                departmentIds: departmentIds
            }
            this.props.postHandler(constants.CLINICS, data);
            this.props.changePanel(constants.CLINICS);
        }else{
            this.setState({error: 'Not all required inputs are filled!'})
        }
    }
    
    render(){
        return(
            <div className="form-panel">
                <div className="page-title">
                    <span>New clinic</span>
                    <button 
                        className="default-btn back"
                        onClick={()=>this.props.changePanel(constants.CLINICS)}
                    >Back</button>
                </div>
                <div className="form">
                    <div className="item-content">
                        <input 
                            placeholder="Name*"
                            value={this.state.name}
                            onChange={(e)=>this.nameChangeHandler(e)}></input>
                        <input 
                            placeholder="Type*"
                            value={this.state.type}
                            onChange={(e)=>this.typeChangeHandler(e)}></input>
                        <Select
                            name="loc-for-cli"
                            placeholder="Localization*"
                            className="selectBox"
                            value={this.state.localization}
                            onChange={this.localizationChangeHandler}
                            options={this.state.localizations}
                        />
                        <Select
                            isMulti
                            placeholder="Departments"
                            className="selectBox"
                            value={this.state.department}
                            onChange={this.departmentChangeHandler}
                            options={this.state.departments}
                        />
                    </div>
                    <div className="item-footer">
                        {this.state.error != null ? <p className="form-error">{this.state.error}</p> : null}
                        <div className="controls">
                            <button className="controls-btn add" onClick={()=>this.submitHandler()}>Add</button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default NewClinic;