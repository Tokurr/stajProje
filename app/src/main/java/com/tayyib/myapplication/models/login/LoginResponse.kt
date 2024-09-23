package com.tayyib.myapplication.models.login

import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("Id")
    var id: Int,

    @SerializedName("Firstname")
    var firstname: String,

    @SerializedName("Lastname")
    var lastname: String,

    @SerializedName("Email")
    var email: String,

    @SerializedName("Password")
    var password: String?,

    @SerializedName("CompanyId")
    var companyId: Int,

    @SerializedName("Type")
    var type: Int,

    @SerializedName("Status")
    var status: Int,

    @SerializedName("eBALicenceUserId")
    var eBALicenceUserId: Any?,

    @SerializedName("InformationMails")
    var informationMails: Any?,

    @SerializedName("UseQDMSSurvey")
    var useQDMSSurvey: Boolean,

    @SerializedName("UseBOYSSurvey")
    var useBOYSSurvey: Boolean,

    @SerializedName("UseEBASurvey")
    var useEBASurvey: Boolean,

    @SerializedName("UseEnsembleSurvey")
    var useEnsembleSurvey: Boolean,

    @SerializedName("IsSubscribe")
    var isSubscribe: Boolean,

    @SerializedName("LoginLanguageId")
    var loginLanguageId: Int,

    @SerializedName("UseCSPSurvey")
    var useCSPSurvey: Boolean,

    @SerializedName("EBACertificate")
    var eBACertificate: Boolean,

    @SerializedName("QDMSCertificate")
    var qDMSCertificate: Boolean,

    @SerializedName("BEAMCertificate")
    var bEAMCertificate: Boolean,

    @SerializedName("ENSEMBLECertificate")
    var eNSEMBLECertificate: Boolean,

    @SerializedName("CSPCertificate")
    var cSPCertificate: Boolean,

    @SerializedName("CreateDate")
    var createDate: String?,

    @SerializedName("ServiceDeskNo")
    var serviceDeskNo: Any?,

    @SerializedName("DontShowAgain")
    var dontShowAgain: Boolean,

    @SerializedName("NotifyInactive")
    var notifyInactive: Any?
)
