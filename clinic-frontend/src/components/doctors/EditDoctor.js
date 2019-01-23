import React, {Component} from 'react';
import constants from '../../constants/pages';
import Select from 'react-select';

class EditDoctor extends Component {
    constructor(props){
        super(props)
        let supervisorsMap = this.props.doctors.map((supervisor)=>{
            const supSup = supervisor.supervisor!=undefined?supervisor.supervisor:supervisor
            if(supervisor.pesel!=this.props.data.pesel&&supSup!=this.props.data.pesel){
                const supDep = supervisor.department!=undefined?supervisor.department.name:'';
                return { value: supervisor, label: <span>{supervisor.firstName+" "+supervisor.lastName+" "}<span className="empty">{supDep}</span></span>}
            }
        });
        let departmentsMap = this.props.departments.map((department)=>{
            // show departments with clinic only
            if(department.clinic!==undefined){
                return { value: department, label: <span>{department.name}<span className="empty">{department.clinic.name}</span></span> }
            }
        });
        supervisorsMap = supervisorsMap.filter(function(el){return el !== undefined;});
        departmentsMap = departmentsMap.filter(function(el){return el !== undefined;});
        this.state={
            firstName: this.props.data.firstName,
            lastName: this.props.data.lastName,
            supervisors: supervisorsMap,
            supervisor: null,
            salary: this.props.data.salary!=undefined ? this.props.data.salary : '',
            speciality: this.props.data.speciality!=undefined ? this.props.data.speciality : '',
            department: null,
            departments: departmentsMap,
            error: null
        }
        this.firstNameChangeHandler = this.firstNameChangeHandler.bind(this);
        this.lastNameChangeHandler = this.lastNameChangeHandler.bind(this);
        this.salaryChangeHandler = this.salaryChangeHandler.bind(this);
        this.specialityChangeHandler = this.specialityChangeHandler.bind(this);
        this.supervisorChangeHandler = this.supervisorChangeHandler.bind(this);
        this.departmentChangeHandler = this.departmentChangeHandler.bind(this);
        this.submitHandler = this.submitHandler.bind(this);
    }

    firstNameChangeHandler(e){
        this.setState({firstName: e.target.value})
    }

    lastNameChangeHandler(e){
        this.setState({lastName: e.target.value})
    }

    supervisorChangeHandler(selectedSup){
        this.setState({supervisor: selectedSup})
    }

    salaryChangeHandler(e){
        this.setState({salary: e.target.value});
    }

    specialityChangeHandler(e){
        this.setState({speciality: e.target.value});
    }

    departmentChangeHandler(selectedDep){
        this.setState({department: selectedDep});
    }

    submitHandler(){
        const supervisorId = this.state.supervisor!=null ? this.state.supervisor.value.pesel : null;
        const departmentId = this.state.department!=null ? this.state.department.value.id : null;
        if( this.state.firstName != '' && this.state.lastName!='' && this.state.pesel!='' && departmentId!=null){
            if((Number.isInteger(parseInt(this.state.salary)))||this.state.salary==''){
                const data = {
                    pesel: this.props.data.pesel,
                    firstName: this.state.firstName,
                    lastName: this.state.lastName,
                    supervisorId: this.state.supervisorId,
                    salary: this.state.salary,
                    speciality: this.state.speciality,
                    departmentId: departmentId,
                    supervisorId: supervisorId
                }
                console.log(data);
                this.props.putHandler(constants.DOCTORS, data);
                this.props.changePanel(constants.DOCTORS);
            }else if (!Number.isInteger(parseInt(this.state.salary))){
                this.setState({error: 'Invalid salary!'})
            }
        }else if( this.state.firstName == '' || this.state.lastName =='' || this.state.supervisorId == '' ){
            this.setState({error: 'Not all required inputs are filled!'})
        }
    }
    
    render(){
        return(
            <div className="form-panel">
                <div className="page-title">
                    <span>Edit doctor</span>
                    <button 
                        className="default-btn back"
                        onClick={()=>this.props.changePanel(constants.DOCTORS)}
                    >Back</button>
                </div>
                <div className="form">
                <div className="item-content">
                        <input 
                            placeholder="First name*"
                            value={this.state.firstName}
                            onChange={(e)=>this.firstNameChangeHandler(e)}></input>
                        <input 
                            placeholder="Last name*"
                            value={this.state.lastName}
                            onChange={(e)=>this.lastNameChangeHandler(e)}></input>
                        <input 
                            placeholder="Salary"
                            value={this.state.salary}
                            onChange={(e)=>this.salaryChangeHandler(e)}></input>
                        <input 
                            placeholder="Speciality"
                            value={this.state.speciality}
                            onChange={(e)=>this.specialityChangeHandler(e)}></input>
                        <Select
                            placeholder="Supervisor"
                            className="selectBox"
                            value={this.state.supervisor}
                            onChange={this.supervisorChangeHandler}
                            options={this.state.supervisors}
                        />
                        <Select
                            placeholder="Department*"
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

export default EditDoctor;