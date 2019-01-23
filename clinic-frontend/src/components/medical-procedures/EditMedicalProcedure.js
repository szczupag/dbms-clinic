import React, {Component} from 'react';
import constants from '../../constants/pages';
import Select from 'react-select';

class EditMedicalProcedure extends Component {
    constructor(props){
        super(props)
        let doctorsMap = this.props.doctors.map(doctor=>{
            return { valueFix: doctor, value: doctor.pesel+'', label:doctor.firstName+" "+doctor.lastName }
        })
        this.state={
            cost: this.props.data.cost,
            doctors: doctorsMap,
            doctor: null,
            name: this.props.data.name,
            error: null
        }
        this.costChangeHandler = this.costChangeHandler.bind(this);
        this.doctorsChangeHandler = this.doctorsChangeHandler.bind(this);
        this.nameChangeHandler = this.nameChangeHandler.bind(this);
        this.submitHandler = this.submitHandler.bind(this);
    }

    costChangeHandler(e){
        this.setState({cost: e.target.value})
    }

    doctorsChangeHandler(selectedDoc){
        this.setState({doctor: selectedDoc})
    }

    nameChangeHandler(e){
        this.setState({name: e.target.value})
    }

    submitHandler(){
        if( this.state.name != '' && this.state.cost!=''){
            if(Number.isInteger(parseInt(this.state.cost))){
                const doctorsIds = this.state.doctor!=null?this.state.doctor.map(doc=>{return doc.valueFix.pesel}):[];
                const data = {
                    id: this.props.data.id,
                    cost: this.state.cost,
                    doctorsIds: doctorsIds,
                    name: this.state.name
                }
                console.log(data);
                this.props.putHandler(constants.MEDICAL_PROCEDURES, data);
                this.props.changePanel(constants.MEDICAL_PROCEDURES);
            }else{
                this.setState({error: 'Invalid cost!'})
            }
        }else if( this.state.name == '' || this.state.cost ==''){
            this.setState({error: 'Not all required inputs are filled!'})
        }
    }
    
    render(){
        return(
            <div className="form-panel">
                <div className="page-title">
                    <span>Edit medical procedure</span>
                    <button 
                        className="default-btn back"
                        onClick={()=>this.props.changePanel(constants.MEDICAL_PROCEDURES)}
                    >Back</button>
                </div>
                <div className="form">
                <div className="item-content">
                        <input 
                            placeholder="Name*"
                            value={this.state.name}
                            onChange={(e)=>this.nameChangeHandler(e)}></input>
                        <input 
                            placeholder="Cost*"
                            value={this.state.cost}
                            onChange={(e)=>this.costChangeHandler(e)}></input>
                        <Select
                            isMulti
                            placeholder="Doctors"
                            className="selectBox"
                            value={this.state.doctor}
                            onChange={this.doctorsChangeHandler}
                            options={this.state.doctors}
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

export default EditMedicalProcedure;