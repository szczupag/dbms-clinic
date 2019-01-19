import React, {Component} from 'react';
import constants from '../../constants/pages';
import Patient from './Patient';
import Select from 'react-select';

class Patients extends Component {
    constructor(props){
        super(props)
        this.state = {
            patients: [],
            sortBy: null
        }
        this.sortChangeHandler = this.sortChangeHandler.bind(this);
    }

    componentDidMount () {
        this.props.getHandler(constants.PATEINTS);
        this.setState({
            patients: this.props.patients
        })
        console.log(this.state.patients)
    }

    componentWillReceiveProps(newProps){
        this.setState({
            patients: newProps.patients
        })
    }

    sortChangeHandler(selectedSort){
        switch(selectedSort.value){
            case 'name':
                this.state.patients.sort((a,b)=>a.lastName>b.lastName);
                break;
            case 'pesel':
                this.state.patients.sort((a,b)=>a.pesel>b.pesel);
                break;
            case 'phone':
                this.state.patients.sort((a,b)=>a.phoneNumber>b.phoneNumber);
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
            <div className="patients">
                <div className="page-title">
                    <span>Patients</span>
                    <button 
                        className="default-btn back"
                        onClick={()=>this.props.changePanel(constants.HOME)}
                    >Back</button>
                </div>
                <div className="options">
                    <button 
                        className="controls-btn add"
                        onClick={()=>this.props.changePanel(constants.NEW_PATIENT)}
                    >Add new patient</button>
                </div>
                <div className="sort">
                    <Select
                        placeholder="Sort by"
                        className="selectBox sort"
                        options={[
                            { value: 'name', label: 'last name' },
                            { value: 'pesel', label: 'PESEL' },
                            { value: 'phone', label: 'phone number' },
                        ]}
                        value={this.state.sortBy}
                        onChange={this.sortChangeHandler}
                    />
                </div>
                <div className="elements">
                {
                    this.state.patients.map( (patient, index) => {
                        return <Patient
                            key={index} 
                            data={patient}
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

export default Patients;