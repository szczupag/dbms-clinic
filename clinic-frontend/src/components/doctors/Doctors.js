import React, {Component} from 'react';
import constants from '../../constants/pages';
import Doctor from './Doctor';
import Select from 'react-select';

class Doctors extends Component {
    constructor(props){
        super(props)
        let departmentsMap = this.props.departments.map((dep,index)=>{
            return { valueFix: dep, value: index, label: <span>{dep.name}<span className="empty">{/*dep.clinic.name*/}</span></span>}
        })
        this.state = {
            doctors: [],
            sortBy: null,
            departments: departmentsMap,
            filterDepartment: null
        }
        this.filterDepartmentChangeHandler = this.filterDepartmentChangeHandler.bind(this);
        this.sortChangeHandler = this.sortChangeHandler.bind(this);
    }

    componentDidMount () {
        this.props.getHandler(constants.DOCTORS);
        this.setState({
            doctors: this.props.doctors
        })
        console.log(this.state.doctors)
    }

    componentWillReceiveProps(newProps){
        this.setState({
            doctors: newProps.doctors
        })
    }

    filterDepartmentChangeHandler(selectedDep){
        const initDoctors = this.props.doctors;
        if(selectedDep.length>0){
            const selectedDepIds = selectedDep.map(sel=>{return sel.valueFix.name});
            console.log(selectedDepIds);
            const filterDoctors = initDoctors.filter(function(doc){
                return selectedDepIds.includes(doc.department.name)?doc:null });
            this.setState({
                filterDepartment: selectedDep,
                doctors: filterDoctors
            })
        }else{
            this.setState({
                filterDepartment: null,
                doctors: initDoctors
            })
        }
    }

    sortChangeHandler(selectedSort){
        switch(selectedSort.value){
            case 'lastname':
                this.state.doctors.sort((a,b)=>a.lastName>b.lastName);
                break;
            case 'pesel':
                this.state.doctors.sort((a,b)=>a.pesel>b.pesel);
                break;
            case 'salary':
                this.state.doctors.sort((a,b)=>a.salary>b.salary);
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
            <div className="doctors">
                <div className="page-title">
                    <span>Doctors</span>
                    <button 
                        className="default-btn back"
                        onClick={()=>this.props.changePanel(constants.HOME)}
                    >Back</button>
                </div>
                <div className="options">
                    <button 
                        className="controls-btn add"
                        onClick={()=>this.props.changePanel(constants.NEW_DOCTOR)}
                    >Add new doctor</button>
                </div>
                <div className="sort">
                    <Select
                        placeholder="Sort by"
                        className="selectBox sort"
                        options={[
                            { value: 'lastname', label: 'last name' },
                            { value: 'pesel', label: 'PESEL' },
                            { value: 'salary', label: 'salary' }
                        ]}
                        value={this.state.sortBy}
                        onChange={this.sortChangeHandler}
                    />
                </div>
                <div className="filters">
                    <Select
                        isMulti
                        placeholder="Filtr department"
                        className="selectBox sort"
                        value={this.state.filterDepartment}
                        onChange={this.filterDepartmentChangeHandler}
                        options={this.state.departments}
                    />
                </div>
                <div className="elements">
                {
                    this.state.doctors.map( (doctor, index) => {
                        return <Doctor
                            key={index} 
                            data={doctor}
                            deleteHandler={this.props.deleteHandler}
                            editItemHandler={this.props.editItemHandler}
                            changePanel={this.props.changePanel}
                            raiseSalary={this.props.raiseSalary}
                            />
                    })
                }
                </div>
            </div>
        )
    }
}

export default Doctors;