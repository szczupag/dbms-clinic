import React, {Component} from 'react';
import constants from '../../constants/pages';
import Select from 'react-select';

class EditTreatment extends Component {
    constructor(props){
        super(props)
        let patientsMap = this.props.patients.map((patient)=>{
            return { value: patient, label: <span>{patient.firstName+" "+patient.lastName}<span className="empty">{patient.pesel}</span></span>}
        });
        let diseasesMap = this.props.diseases.map((disease)=>{
            return {value: disease, label: <span>{disease.name}<span className="empty">{disease.serverity}</span></span>}
        });
        let medicalProceduresMap = this.props.medicalProcedures.map((medicalProcedure)=>{
            return {valueFix: medicalProcedure, value: medicalProcedure.id+'', label: medicalProcedure.name}
        });
        this.state={
            medicalProcedures: medicalProceduresMap,
            medicalProcedure: null,
            patient: null,
            patients: patientsMap,
            diseases: diseasesMap,
            disease: null,
            startDate: this.props.data.startDate,
            endDate: this.props.data.endDate,
            error: null
        }
        this.patientChangeHandler = this.patientChangeHandler.bind(this);
        this.startDateChangeHandler = this.startDateChangeHandler.bind(this);
        this.endDateChangeHandler = this.endDateChangeHandler.bind(this);
        this.diseaseChangeHandler = this.diseaseChangeHandler.bind(this);
        this.medicalProceduresChangeHandler = this.medicalProceduresChangeHandler.bind(this);
        this.submitHandler = this.submitHandler.bind(this);
    }

    patientChangeHandler(selectedPatient){
        this.setState({patient: selectedPatient});
    }

    startDateChangeHandler(e){
        this.setState({startDate: e.target.value})
    }

    endDateChangeHandler(e){
        this.setState({endDate: e.target.value})
    }

    diseaseChangeHandler(selectedDis){
        this.setState({disease: selectedDis});
    }

    medicalProceduresChangeHandler(selectedMedProc){
        this.setState({medicalProcedure: selectedMedProc})
    }

    submitHandler(){
        if( this.state.startDate != '' && this.state.patient!=null){
            const medicalProceduresIds = this.state.medicalProcedure!=null?this.state.medicalProcedure.map(med=>{return med.valueFix.id}):[];
            const diseaseId = this.state.disease!=undefined?this.state.disease.value.id:'';
            const data = {
                id: this.props.data.id,
                patientPesel: this.state.patient.value.pesel,
                startDate: this.state.startDate,
                endDate: this.state.endDate,
                diseaseId: diseaseId,
                medicalProceduresIds: medicalProceduresIds
            }
            console.log(data);
            this.props.putHandler(constants.TREATMENTS, data);
            this.props.changePanel(constants.TREATMENTS);
        }else {
            this.setState({error: 'Not all required inputs are filled!'})
        }
    }
    
    render(){
        return(
            <div className="form-panel">
                <div className="page-title">
                    <span>Edit treatment</span>
                    <button 
                        className="default-btn back"
                        onClick={()=>this.props.changePanel(constants.TREATMENTS)}
                    >Back</button>
                </div>
                <div className="form">
                    <div className="item-content">
                        <Select
                            placeholder="Patient*"
                            className="selectBox"
                            value={this.state.patient}
                            onChange={this.patientChangeHandler}
                            options={this.state.patients}
                        />
                        <input 
                            placeholder="Start date*"
                            type="date"
                            value={this.state.startDate}
                            onChange={(e)=>this.startDateChangeHandler(e)}></input>
                        <input 
                            placeholder="End date"
                            type="date"
                            value={this.state.endDate}
                            onChange={(e)=>this.endDateChangeHandler(e)}></input>
                        <Select
                            placeholder="Disease"
                            className="selectBox"
                            value={this.state.disease}
                            onChange={this.diseaseChangeHandler}
                            options={this.state.diseases}
                        />
                        <Select
                            isMulti
                            placeholder="Medical Procedure"
                            className="selectBox"
                            value={this.state.medicalProcedure}
                            onChange={this.medicalProceduresChangeHandler}
                            options={this.state.medicalProcedures}
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

export default EditTreatment;