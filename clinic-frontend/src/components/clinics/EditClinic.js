import React, {Component} from 'react';
import constants from '../../constants/pages';
import Select from 'react-select';

class EditClinic extends Component {
    constructor(props){
        super(props)
        let localizationMap = this.props.localizations.map((localization)=>{
            if (localization.clinic===undefined||localization.clinic==this.props.data.name) {
                return { value: localization, label: localization.street+" "+localization.postalCode+" "+localization.city+" "+localization.id}
            }
        });
        let departmentsMap = this.props.departments.map((department)=>{
            return { valueFix: department, value: department+'', label: department.name }
        });
        localizationMap = localizationMap.filter(function(el){return el !== undefined;});
        departmentsMap = departmentsMap.filter(function(el){return el !== undefined;});
        this.state={
            name: this.props.data.name,
            type: this.props.data.type,
            departments: departmentsMap,
            department: null,
            localizations: localizationMap,
            localization: this.props.localization,
            error: null
        }
        this.nameChangeHandler = this.nameChangeHandler.bind(this);
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
        if( this.state.name != '' && this.state.type!='' && this.state.localizationId == null){
            const data = {
                id: this.props.data.id,
                name: this.state.name,
                type: this.state.type,
                localizationId: localizationId,
                departmentIds: departmentIds
            }
            this.props.putHandler(constants.CLINICS, data);
            this.props.changePanel(constants.CLINICS);
        
        }else if(this.state.name == '' || this.state.type ==''){
            this.setState({error: 'Not all required inputs are filled!'})
        }
    }
    
    render(){
        return(
            <div className="form-panel">
                <div className="page-title">
                    <span>Edit clinic</span>
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
                            <button className="controls-btn add" onClick={()=>this.submitHandler()}>Edit</button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default EditClinic;