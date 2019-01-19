import React, {Component} from 'react';
import constants from '../../constants/pages';
import MedicalProcedure from './MedicalProcedure';
import Select from 'react-select';

class MedicalProcedures extends Component {
    constructor(props){
        super(props)
        let doctorsMap = []
        this.props.medicalProcedures.map(med=>{
            med.doctors.map(doc=>doctorsMap.push({
                valueFix: doc, 
                value: doc.pesel+'', 
                label: doc.firstName+" "+doc.lastName}))
        })

        this.state = {
            medicalProcedures: [],
            doctors: doctorsMap,
            sortBy: null,
            filtrDoctor: null,
        }
        this.sortChangeHandler = this.sortChangeHandler.bind(this);
        this.filtrDoctorChangeHandler = this.filtrDoctorChangeHandler.bind(this);
    }

    componentDidMount () {
        this.props.getHandler(constants.MEDICAL_PROCEDURES);
        this.setState({
            medicalProcedures: this.props.medicalProcedures
        })
        console.log(this.state.medicalProcedure)
    }

    componentWillReceiveProps(newProps){
        this.setState({
            medicalProcedures: newProps.medicalProcedures
        })
    }

    sortChangeHandler(selectedSort){
        switch(selectedSort.value){
            case 'name':
                this.state.medicalProcedures.sort((a,b)=>a.name>b.name);
                break;
            case 'cost':
                this.state.medicalProcedures.sort((a,b)=>a.cost>b.cost);
                break;
            default:
                break;
        }
        this.setState({
            sortBy: selectedSort
        })
    }

    filtrDoctorChangeHandler(selectedDoc){
        const initProc = this.props.medicalProcedures;
        if(selectedDoc.length>0){
            const selectedDocFix = selectedDoc.map(doc=>{return doc.valueFix.pesel})
            const filteredProc = initProc.filter(function(proc){
                let include = false;
                proc.doctors.map(doc=>{
                    selectedDocFix.includes((doc.pesel))?include=true:null;
                })
                console.log(proc,include,selectedDoc)
                return include?proc:null
            });
            this.setState({
                filtrDoctor: selectedDoc, 
                medicalProcedures: filteredProc})
        }else{
            this.setState({
                filtrDoctor: null,
                medicalProcedures: initProc
            })
        }
    }

    render(){
        return(
            <div className="medical-procedure">
                <div className="page-title">
                    <span>Medical procedures</span>
                    <button 
                        className="default-btn back"
                        onClick={()=>this.props.changePanel(constants.HOME)}
                    >Back</button>
                </div>
                <div className="options">
                    <button 
                        className="controls-btn add"
                        onClick={()=>this.props.changePanel(constants.NEW_MEDICAL_PROCEDURE)}
                    >Add new medical procedure</button>
                </div>
                <div className="sort">
                    <Select
                        placeholder="Sort by"
                        className="selectBox sort"
                        options={[
                            { value: 'name', label: 'name' },
                            { value: 'cost', label: 'cost' }
                        ]}
                        value={this.state.sortBy}
                        onChange={this.sortChangeHandler}
                    />
                </div>
                <div className="filters">
                    <Select
                        isMulti
                        placeholder="Filtr doctor"
                        className="selectBox sort"
                        value={this.state.filtrDoctor}
                        onChange={this.filtrDoctorChangeHandler}
                        options={this.state.doctors}
                    />
                </div>
                <div className="elements">
                {
                    this.state.medicalProcedures.map( (procedure, index) => {
                        return <MedicalProcedure
                            key={index} 
                            data={procedure}
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

export default MedicalProcedures;