import React, {Component} from 'react';
import constants from '../../constants/pages';

class EditTreatment extends Component {
    constructor(props){
        super(props)
        this.state={
            patientPesel: this.props.data.patientPesel,
            diseaseId: this.props.data.diseaseId,
            startDate: this.props.data.startDate,
            endDate: this.props.data.endDate,
            medicalProceduresIds: this.props.medicalProceduresIds,
            error: null
        }
        this.patientChangeHandler = this.patientChangeHandler.bind(this);
        this.startDateChangeHandler = this.startDateChangeHandler.bind(this);
        this.endDateChangeHandler = this.endDateChangeHandler.bind(this);
        this.diseaseChangeHandler = this.diseaseChangeHandler.bind(this);
        this.medicalProceduresChangeHandler = this.medicalProceduresChangeHandler.bind(this);
        this.submitHandler = this.submitHandler.bind(this);
    }

    patientChangeHandler(e){
        this.setState({patientPesel: e.target.value})
    }

    startDateChangeHandler(e){
        this.setState({startDate: e.target.value})
    }

    endDateChangeHandler(e){
        this.setState({endDate: e.target.value})
    }

    diseaseChangeHandler(e){
        this.setState({diseaseId: e.target.value})
    }

    medicalProceduresChangeHandler(e){
        this.setState({medicalProceduresIds: e.target.value})
    }

    submitHandler(){
        if( this.state.startDate != '' && this.state.patientPesel!=''){
            if( this.state.startDate != this.props.data.startDate || this.state.patientPesel != this.props.data.patientPesel){
                const data = {
                    id: this.props.data.id,
                    patientPesel: this.state.patientPesel,
                    startDate: this.state.startDate,
                    endDate: this.state.endDate,
                    diseaseId: this.state.diseaseId,
                    medicalProceduresIds: this.state.medicalProcedures
                }
                console.log(data);
                this.props.putHandler(constants.TREATMENTS, data);
                this.props.changePanel(constants.TREATMENTS);
            }else{
                this.setState({error: 'There are no updates for this treatment'})
            }
        }else if( this.state.startDate == '' || this.state.patientPesel ==''){
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
                        <input 
                            placeholder="Patient*"
                            value={this.state.patient}
                            onChange={(e)=>this.patientChangeHandler(e)}></input>
                        <input 
                            placeholder="Start date*"
                            value={this.state.startDate}
                            onChange={(e)=>this.startDateChangeHandler(e)}></input>
                        <input 
                            placeholder="End date"
                            value={this.state.endDate}
                            onChange={(e)=>this.endDateChangeHandler(e)}></input>
                        <input 
                            placeholder="Disease"
                            value={this.state.disease}
                            onChange={(e)=>this.diseaseChangeHandler(e)}></input>
                        <input 
                            placeholder="Medical procedures"
                            value={this.state.medicalProcedures}
                            onChange={(e)=>this.medicalProceduresChangeHandler(e)}></input>
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