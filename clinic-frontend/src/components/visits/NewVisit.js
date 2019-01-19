import React, {Component} from 'react';
import constants from '../../constants/pages';
import Select from 'react-select';

class NewVisit extends Component {
    constructor(props){
        super(props)
        let patientsMap = this.props.patients.map(patient=>{
            return { value: patient, label: <p>{patient.firstName+" "+patient.lastName}<span className="empty">{patient.pesel}</span></p>}
        });
        let visitorsMap = this.props.visitors.map(visitor=>{
            return { value: visitor, label: <p>{visitor.firstName+" "+visitor.lastName}<span className="empty">{visitor.pesel}</span></p>}
        });
        this.state={
            patient: null,
            patients: patientsMap,
            visitor: null,
            visitors: visitorsMap,
            visitDate: '',
            error: null
        }
        this.patientChangeHandler = this.patientChangeHandler.bind(this);
        this.visitorChangeHandler = this.visitorChangeHandler.bind(this);
        this.visitDateChangeHandler = this.visitDateChangeHandler.bind(this);
        this.submitHandler = this.submitHandler.bind(this);
    }

    patientChangeHandler(selected){
        this.setState({patient: selected})
    }

    visitDateChangeHandler(e){
        this.setState({visitDate: e.target.value})
    }

    visitorChangeHandler(selected){
        this.setState({visitor: selected})
    }

    submitHandler(){
        if(this.state.visitDate != '' && this.state.patient!=null, this.state.visitor!=null){
            const data = {
                patientPesel: this.state.patient.value.pesel,
                visitDate: this.state.visitDate,
                visitorPesel: this.state.visitor.value.pesel,
            }
            console.log(data);
            this.props.postHandler(constants.VISITS, data);
            this.props.changePanel(constants.VISITS);
        }else{
            this.setState({error: 'Not all required inputs are filled!'})
        }
    }
    // 
    render(){
        return(
            <div className="form-panel">
                <div className="page-title">
                    <span>New treatment</span>
                    <button 
                        className="default-btn back"
                        onClick={()=>this.props.changePanel(constants.VISITS)}
                    >Back</button>
                </div>
                <div className="form">
                    <div className="item-content">
                        <Select
                            name="loc-for-cli"
                            placeholder="Patient*"
                            className="selectBox"
                            value={this.state.patient}
                            onChange={this.patientChangeHandler}
                            options={this.state.patients}
                        />
                        <Select
                            name="loc-for-cli"
                            placeholder="Visitor*"
                            className="selectBox"
                            value={this.state.visitor}
                            onChange={this.visitorChangeHandler}
                            options={this.state.visitors}
                        />
                        <input 
                            placeholder="Visit date*"
                            type="date"
                            value={this.state.visitDate}
                            onChange={(e)=>this.visitDateChangeHandler(e)}></input>
                    </div>
                    <div className="item-footer">
                        {this.state.error != null ? <p className="form-error">{this.state.error}</p> : null}
                        <div className="controls">
                            <button className="controls-btn add" onClick={()=>this.submitHandler()}>Add</button>
                        </div>,
                    </div>
                </div>
            </div>
        );
    }
}

export default NewVisit;